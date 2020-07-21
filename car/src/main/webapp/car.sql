drop database car;
create database car;

INSERT INTO shop (`id`, `create_date`, `create_user`, `shopname`, `shop_status`, `update_date`, `address`) VALUES ('2', now(), 'admin', 'พี่แขก', '1', now(), '1234');
select * from shop;

INSERT INTO user (`id`, `password`, `username`, `shop_id`) VALUES ('1', password('ying2002'), 'terapong', '1');
select * from user;

select * from color;
