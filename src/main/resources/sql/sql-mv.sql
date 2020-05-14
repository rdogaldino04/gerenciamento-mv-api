create table tb_profissional (
  id number(9) not null, 
  nome varchar2(100) not null, 
  endereco varchar2(100) not null, 
  primary key (id)
);
create sequence sq_prof start with 1 increment by 1;

create table tb_estabelecimento (
  id number(9) not null, 
  nome varchar2(100) not null, 
  endereco varchar2(100) not null, 
  primary key (id)
);
create sequence sq_estab start with 1 increment by 1;

create table tb_estab_prof (
	estab_id number(9) not null, 
	prof_id number(9) not null
);

alter table tb_estab_prof add constraint fk_estab_prof_prof 
foreign key (prof_id) references tb_profissional;

alter table tb_estab_prof add constraint fk_estab_prof_estab 
foreign key (estab_id) references tb_estabelecimento;
