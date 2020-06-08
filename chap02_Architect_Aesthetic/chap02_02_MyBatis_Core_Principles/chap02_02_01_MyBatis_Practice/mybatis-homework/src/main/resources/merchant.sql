CREATE database merchant;

use merchant;

CREATE table merchant
(
    id         integer(10) not null,
    name       varchar(10),
    biz_scopes varchar(20),
    primary key (id)
) engine = Innodb,
  auto_increment = 1