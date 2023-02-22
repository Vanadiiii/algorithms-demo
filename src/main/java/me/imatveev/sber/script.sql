create table author (
id uuid primary key,
name varchar2(80) unique not null,
photo_id uuid
);

create table book (
id uuid primary key,
name varchar2(200) not null
);

create table author_book_link (
author_id uuid,
book_id uuid,
primary key (author_id, book_id)
);

-- все авторы, которые не были никогда соавторами

select a.* -- exclude this authors
from author a
where a.id not in (
    select l.author_id -- find all authors, written this books
    from author_book_link l
    where book_id in (
        select book_id -- find all book, written by many authors
        from author_book_link
        group by book_id
        having count(*) > 1
    )
);
