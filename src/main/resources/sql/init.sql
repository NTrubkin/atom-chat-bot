DROP TABLE IF EXISTS learning_log;
DROP TABLE IF EXISTS command_tag;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS command;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS chat_user;


create table chat_user
(
    id       bigserial    not null
        constraint user_pk
            primary key,
    login    varchar(40)  not null,
    password varchar(120) not null
);

create unique index chat_user_login_uindex
    on chat_user (login);

create table message
(
    id        bigserial not null
        constraint message_pk
            primary key,
    text      text      not null,
    timestamp bigint    not null,
    read      boolean   not null,
    owner_id  bigint    not null
        constraint message_owner_id_fk
            references chat_user
            on update cascade on delete cascade
);

create table command
(
    id                bigserial   not null
        constraint command_pk
            primary key,
    code              varchar(24) not null,
    description       varchar(100),
    user_command      boolean     not null,
    reply             text        not null,
    parent_command_id bigint
        constraint command_parent_command_id_fk
            references command
            on update cascade on delete cascade
);

create unique index command_code_uindex
    on command (code);


create table tag
(
    id        bigserial    not null
        constraint tag_pk
            primary key,
    name      varchar(100) not null,
    idf_value float        not null
);

create unique index tag_name_uindex
    on tag (name);

create table command_tag
(
    id         bigserial not null
        constraint command_tag_pk
            primary key,
    command_id bigint    not null
        constraint command_tag_command_id_fk
            references command
            on update cascade on delete cascade,
    tag_id     bigint    not null
        constraint command_tag_tag_id_fk
            references tag
            on update cascade on delete cascade
);

create table learning_log
(
    id           bigserial    not null
        constraint learning_log_pk
            primary key,
    command_code varchar(100) not null,
    text         text         not null,
    date         bigint       not null
);

insert into chat_user (id, login, password)
values (1, 'it', '$2a$10$0Qlf.upVSrL01yfidrqoiuMa8AE9DQmLzMklxfYyIRBfxUWw24JXe');
insert into chat_user (id, login, password)
values (2, 'hr', '$2a$10$0Qlf.upVSrL01yfidrqoiuMa8AE9DQmLzMklxfYyIRBfxUWw24JXe');
insert into chat_user (id, login, password)
values (3, 'nt', '$2a$10$0Qlf.upVSrL01yfidrqoiuMa8AE9DQmLzMklxfYyIRBfxUWw24JXe');

insert into command (id, code, description, user_command, reply, parent_command_id)
values (1, 'root', 'root command', true, '', null);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (2, 'hi', 'hi command', true, 'Привет.{terminal}', 1);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (3, 'help', 'help', true,
        'Доступны следующие команды: "приветствие", "узнать должность", "узнать зарплату", "узнать стаж", "заявка на оборудование".{terminal}',
        1);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (4, 'get_pos', 'working position', true, 'Ваша рабочая должность "{pos}".{terminal}', 1);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (5, 'get_sal', 'salary', true, 'Ваша зарплата "{sal}" рублей.{terminal}', 1);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (6, 'get_exp', 'working experience', true, 'Вы были наняты "{hire}" числа, текущий стаж "{exp}" дней.{terminal}',
        1);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (7, 'it_req', 'it request', true,
        'Давайте составим заявку в IT-отдел. Пожалуйста укажите детали заявки.{it}', 1);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (8, 'vacation_req', 'hr vacation request', true,
        'Давайте составим заявку в HR-отдел. Пожалуйста укажите детали заявки.{terminal}', 1);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (9, 'equip_type', '', true, 'Тип оборудования записан.{equip}{back}', 7);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (10, 'it_send', '', true, 'Заявка успешно отправлена.{send}{terminal}', 7);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (11, 'it_cancel', '', true, 'Заявка в IT-отдел отменена.{terminal}', 7);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (12, 'it_help', '', true,
        'Чтобы отправить заявку в IT-отдел, необходимо указать тип оборудования и подтвердить отправку сообщения.{back}',
        7);
insert into command (id, code, description, user_command, reply, parent_command_id)
values (13, 'it_text', '', true, 'Текущая заявка в IT-отдел: {message}', 7);

insert into tag (id, name, idf_value)
values (1, 'привет', 0),
       (2, 'помощь', 0),
       (3, 'должность', 0),
       (4, 'зарплата', 0),
       (5, 'стаж', 0),
       (6, 'оборудование', 0),
       (7, 'отпуск', 0),
       (8, 'тип', 0),
       (9, 'отправлять', 0),
       (10, 'отменять', 0),
       (11, 'текст', 0);
insert into command_tag (command_id, tag_id)
values (2, 1),
       (3, 2),
       (4, 3),
       (5, 4),
       (6, 5),
       (7, 6),
       (8, 7),
       (9, 8),
       (10, 9),
       (11, 10),
       (12, 2),
       (13, 11);

ALTER SEQUENCE chat_user_id_seq RESTART WITH 1000;
ALTER SEQUENCE command_id_seq RESTART WITH 1000;
ALTER SEQUENCE command_tag_id_seq RESTART WITH 1000;
ALTER SEQUENCE learning_log_id_seq RESTART WITH 1000;
ALTER SEQUENCE message_id_seq RESTART WITH 1000;
ALTER SEQUENCE tag_id_seq RESTART WITH 1000;