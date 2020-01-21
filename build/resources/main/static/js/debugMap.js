mapboxgl.accessToken = 'pk.eyJ1IjoidHVlc2Q0eSIsImEiOiJjazI1YWVwbmYyYjM0M25tdnRiOHRheTVuIn0.0AeSp790OAIjZURwF1HzGw';

function getRandomColor() {
    let letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function metersToPixelsAtMaxZoom(meters, latitude = 48) {
    return meters / 0.075 / Math.cos(latitude * Math.PI / 180);
}

// fetch necessary data

let data = {
    vehicles: [],
    users: [],
    rentals: [],
    events: [],
    routes: [],
    positions: []
};
let actions = {
    positions: function () {
        // setup color and opacity map for positions
        let vIds = [...new Set(data.vehicles.map(v => v.id))];
        let vColors = {};
        let vOpacitySteps = {};
        let opacities = {};

        vIds.forEach(id => {
            vColors[id] = getRandomColor();
            vOpacitySteps[id] = .8 / data.positions.filter(p => p.vehicleIdentification === id).length;
            opacities[id] = .2;
        });

        // map positions to correct featureCollection
        let pos = turf.featureCollection(data.positions
            .map(p => {
                opacities[p.vehicleIdentification] += vOpacitySteps[p.vehicleIdentification];
                return turf.point([p.latitude, p.longitude], {
                    vehicle: p.vehicleIdentification,
                    dateTime: p.dateTime,
                    color: vColors[p.vehicleIdentification],
                    opacity: opacities[p.vehicleIdentification]
                })
            }));

        // add to map
        map.addLayer({
            id: 'positions',
            type: 'circle',
            source: {
                type: 'geojson',
                data: pos
            },
            paint: {
                // 'icon-image': '{icon}-15',
                'circle-radius': {
                    'base': 1.75,
                    'stops': [
                        [12, 2],
                        [22, 180]
                    ]
                },
                'circle-color': {"type": "identity", "property": "color"},
                'circle-opacity': {"type": "identity", "property": "opacity"}
            }
        });

        // create popup for on-hover
        const popup = new mapboxgl.Popup({
            closeButton: false,
            closeOnClick: false
        });

        map.on('mouseenter', 'positions', function (e) {
            map.getCanvas().style.cursor = 'pointer';

            var coordinates = e.features[0].geometry.coordinates.slice();
            var props = e.features[0].properties;
            while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
                coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
            }
            popup
                .setLngLat(coordinates)
                .setHTML(`<p>Vehicle: #${props.vehicle}</p><p>at ${props.dateTime}</p> `)
                .addTo(map);
        });

        map.on('mouseleave', 'positions', function () {
            map.getCanvas().style.cursor = '';
            popup.remove();
        });
    },
    events: function () {

        let events = turf.featureCollection(data.events.map(e => turf.point([e.longitude, e.latitude], {
            description: e.description,
            type: e.type,
            risk: e.risk,
            timeLoss: e.timeLoss,
            timeStamp: e.timeStamp,
            number: e.id
        })));

        map.addLayer({
            id: 'events',
            type: 'circle',
            source: {
                type: 'geojson',
                data: events
            },
            'paint': {
                'circle-radius': {
                    stops: [
                        [0, 0],
                        [20, metersToPixelsAtMaxZoom(1000, 48)]
                    ],
                    base: 2
                },
                'circle-color': 'red',
                'circle-opacity': .4
            }
        });

        // create popup for on-hover
        const popup = new mapboxgl.Popup({
            closeButton: false,
            closeOnClick: false
        });

        map.on('mouseenter', 'events', function (e) {
            map.getCanvas().style.cursor = 'pointer';

            var coordinates = e.features[0].geometry.coordinates.slice();
            var props = e.features[0].properties;
            while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
                coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
            }
            popup
                .setLngLat(coordinates)
                .setHTML(`<h3>${props.type} #${props.number}</h3>
                        <p>${props.description}</p>
                        <p>Risk ${props.risk} of ${props.timeLoss} time loss</p>`)
                .addTo(map);
        });

        map.on('mouseleave', 'events', function () {
            map.getCanvas().style.cursor = '';
            popup.remove();
        });
    },
    routes: function () {
        map.addLayer({
            id: 'routes',
            type: 'line',
            source: {
                type: 'geojson',
                data: {
                    type: 'Feature',
                    properties: {},
                    geometry: data.vehicles
                }
            },
            'layout': {
                'line-join': 'round',
                'line-cap': 'round'
            },
            'paint': {
                'line-color': 'red',
                'line-width': 6
            }
        });
    }
};

let map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/light-v10',
    center: [14.2858, 48.3069],
    zoom: 9
});

const fetchEntities = function (entity) {
    fetch("/api/" + entity)
        .then(res => res.json())
        .then(j => j._embedded[entity])
        .then(v => {
            data[entity] = v;
            if (!!actions[entity]) {
                actions[entity]();
            }
        });
};

const load = function () {
    for (let entity in data) {
        fetchEntities(entity);
    }
};

map.on('load', load);
