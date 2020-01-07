create extension if not exists POSTGIS;

create table if not exists dnormal_user
(
    id bigint not null
        constraint dnormal_user_pkey
            primary key,
    email_address varchar(255),
    name varchar(255)
);

create table if not exists droute
(
    id bigint not null
        constraint droute_pkey
            primary key
);

create table if not exists droute_locations
(
    droute_id bigint not null
        constraint fkkda4rs63hmxqa7dwn7a8537ry
            references droute,
    latitude double precision not null,
    location_point geometry,
    longitude double precision not null
);

alter table droute_locations owner to dev;

create table if not exists dtransport_manager
(
    id bigint not null
        constraint dtransport_manager_pkey
            primary key,
    email_address varchar(255),
    latitude double precision not null,
    location_point geometry,
    longitude double precision not null,
    name varchar(255)
);

create table if not exists dvehicle
(
    id bigint not null
        constraint dvehicle_pkey
            primary key,
    type varchar(255)
);

create table if not exists dmaintenance
(
    id bigint not null
        constraint dmaintenance_pkey
            primary key,
    end_time timestamp,
    problem_description varchar(255),
    start_time timestamp,
    vehicle_id bigint
        constraint fkauexq9m250kbwse283wl5wtw4
            references dvehicle
);

create table if not exists dposition
(
    id bigint not null
        constraint dposition_pkey
            primary key,
    date_time timestamp,
    latitude double precision not null,
    location_point geometry,
    longitude double precision not null,
    vehicle_id bigint
        constraint fkigbnnll7c5s2puyj4wgbyx68n
            references dvehicle
);

create table if not exists dredistribution_task
(
    id bigint not null
        constraint dredistribution_task_pkey
            primary key,
    date_time timestamp,
    redistributor_id bigint
        constraint fkf6kwgt1r6pciuo0e0det77tp6
            references dtransport_manager,
    vehicle_id bigint
        constraint fk494lynll5me7oh8cq86brn8nv
            references dvehicle
);

create table if not exists drental
(
    id bigint not null
        constraint drental_pkey
            primary key,
    end_time timestamp,
    start_time timestamp,
    renter_id bigint
        constraint fkcse979j2qdn8cmiwoto2gnbpw
            references dnormal_user,
    vehicle_id bigint
        constraint fkm86afdoj1fq4sc196w35ruyym
            references dvehicle
);

create table if not exists dvehicle_rentals
(
    dvehicle_id bigint not null
        constraint fk378urmnahcd6060ssy92m6qdv
            references dvehicle,
    rentals_id bigint not null
        constraint uk_cixk5cco21b5t5et3grhc77k0
            unique
        constraint fks37f0xplqrdsoumrau9thetsl
            references drental
);

