create table user_info (
    id bigserial primary key,
    name varchar(64) not null,
    age integer DEFAULT null,
    created_at timestamp with time zone DEFAULT NOW(),
    updated_at timestamp with time zone DEFAULT null
);