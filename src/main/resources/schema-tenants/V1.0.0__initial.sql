create table ${flyway:defaultSchema}.sync_error
(
  id               serial primary key not null,
  task_uuid        uuid               not null,
  error_text       text               not null,
  error_stacktrace text               not null,
  occurrences      integer            not null default 1,
  created_at       timestamptz        not null default now(),
  updated_at       timestamptz        not null default now()
);
