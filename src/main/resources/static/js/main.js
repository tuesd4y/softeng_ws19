mapboxgl.accessToken = 'pk.eyJ1IjoidHVlc2Q0eSIsImEiOiJjazI1YWVwbmYyYjM0M25tdnRiOHRheTVuIn0.0AeSp790OAIjZURwF1HzGw';
$.ajaxSettings.traditional = true;

window.mapLoaded = false;

const settings = {
    mode: 'all',
    points: null
};

let selectedVehicle = null;

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
    } catch(err) {
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
    selectedVehicle = $('#vid').val();
    if (settings.mode === "all") {
        $.get("http://localhost:8080/api/positions/search/findTop1ByVehicleIdOrderByDateTimeDesc?vehicleId=" + selectedVehicle, function (res) {
            settings.points = [res];
            displayData()
        })
    } else {
        $.get("http://localhost:8080/api/positions/search/findAllByVehicleId?vehicleId=" + selectedVehicle, function (res) {
            settings.points = res._embedded.positions;
            displayData()
        })
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


