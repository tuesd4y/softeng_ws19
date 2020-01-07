// fetch necessary data

data = {
    vehicles: [],
    users: [],
    rentals: [],
};

const actions = {
    vehicles: () => {
        document.querySelectorAll('#newrental-vid,#fake-vid').forEach(el => el.innerHTML = '');
            data.vehicles.forEach(v => {
                document.querySelectorAll('#newrental-vid,#fake-vid').forEach(i => i
                    .insertAdjacentHTML('beforeend', `<option value="${v.id}">${v.type} (#${v.id})</option>`))
            });
    },
    users: () => {
        document.querySelectorAll('#newrental-uid').forEach(el => el.innerHTML = '');
        data.users.forEach(v => {
            document.querySelectorAll('#newrental-uid').forEach(i => i
                .insertAdjacentHTML('beforeend', `<option value="${v.id}">${v.name} (#${v.id})</option>`))
        });
    },
    rentals: () => {
        document.querySelectorAll('#return-rid').forEach(el => el.innerHTML = '');
        data.rentals.forEach(v => {
            document.querySelectorAll('#return-rid').forEach(i => i
                .insertAdjacentHTML('beforeend', `<option value="${v.id}">${v.id} - since ${v.startTime}</option>`))
        });
    }
};

const fetchEntities = function (entity) {
    fetch("/api/" + entity)
        .then(res => res.json())
        .then(j => j._embedded[entity])
        .then(v => {
            data[entity] = v;
            actions[entity]();
        });
};

function postData(url = '', data = {}, callback) {
    fetch(url, {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(callback)
}

function patchData(url = '', data = {}, callback) {
    fetch(url, {
        method: 'PATCH',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(callback)
}

const load = function () {
    for (let entity in data) {
        fetchEntities(entity);
    }
};

document.querySelector('#create-vehicle').addEventListener('click', function () {
    let type = document.getElementById('v-type').value;
    postData('/api/vehicles', {type}, () => fetchEntities('vehicles'));
});
document.querySelector('#create-user').addEventListener('click', function () {
    let email = document.getElementById('user-email').value;
    let name = document.getElementById('user-name').value;
    postData('/api/users', {emailAddress: email, name}, () => fetchEntities('users'));
});
document.querySelector('#newrental').addEventListener('click', function () {
    let vid = document.getElementById('newrental-vid').value;
    let uid = document.getElementById('newrental-uid').value;
    postData('/api/rentals', {renter: '/api/users/' + uid, vehicle: '/api/vehicles/' + vid}, () => fetchEntities('rentals'));
});
document.querySelector('#return').addEventListener('click', function () {
    let rid = document.getElementById('return-rid').value;
    patchData('/api/rentals/' + rid, {endTime: new Date().toISOString().split('Z')[0]}, () => fetchEntities('rentals'));
});

load();
