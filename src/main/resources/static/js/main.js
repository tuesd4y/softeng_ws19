mapboxgl.accessToken = 'pk.eyJ1IjoidHVlc2Q0eSIsImEiOiJjazI1YWVwbmYyYjM0M25tdnRiOHRheTVuIn0.0AeSp790OAIjZURwF1HzGw';
$.ajaxSettings.traditional = true;

window.mapLoaded = false;

const settings = {
    mode: 'all',
    points: null,
    vehicle: 'all'
};

$('.modes>a').on('click', function () {
    $('.modes>a').toggleClass('on').toggleClass('off');
    if (settings.mode === 'all') {
        settings.mode = 'last';
    } else {
        settings.mode = 'all'
    }
    fetchData();
});

let map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/light-v10',
    center: [14.2858, 48.3069],
    zoom: 10
});

function displayData() {
    try {
        map.removeLayer('points');
        map.removeSource('points');
    } catch (err) {
        // ignored
    }
    map.addLayer({
        id: 'points',
        type: 'circle',
        source: {
            type: 'geojson',
            data: {
                type: 'FeatureCollection',
                features: settings.points.map(function (v) {
                    return {
                        type: 'Feature',
                        geometry: {
                            type: 'Point',
                            coordinates: [v.latitude, v.longitude]
                        }
                    }
                })
            }
        }
    })
}

function fetchData() {
    settings.vehicle = $('#vid').val();
    if (settings.vehicle === "all") {
        if (settings.mode === "all") {
            $.get("/api/positions", function (res) {
                settings.points = res._embedded.positions;
                displayData()
            })
        } else {
            $.get("/api/positions/search/findCurrentOfAllVehicles", function (res) {
                settings.points = res._embedded.positions;
                displayData()
            })
        }
    } else {
        if (settings.mode === "all") {
            $.get("/api/positions/search/findAllByVehicleId?vehicleId=" + settings.vehicle, function (res) {
                settings.points = res._embedded.positions;
                displayData()
            });
        } else {
            $.get("/api/positions/search/findTop1ByVehicleIdOrderByDateTimeDesc?vehicleId=" + settings.vehicle, function (res) {
                settings.points = [res];
                displayData()
            });
        }
    }
}


map.on('load', function () {
    window.mapLoaded = true;
    if (settings.points) {
        displayData()
    } else {
        fetchData()
    }
});

$('#vid').change(function () {
    fetchData()
});

$.get('/api/vehicles', function (data) {
    let vehicles = data._embedded.vehicles;
    $('#vid').empty();

    vehicles.forEach(function (v) {
        $('#vid').append($('<option value="' + v.id + '">Vehicle ' + v.id + '</option>'));
    });
    $('#vid').append($('<option value="all">All Vehicles</option>'));
});

