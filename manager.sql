/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/*==============================================================*/


drop table if exists tb_department;

drop table if exists tb_dictionary;

drop table if exists tb_employee;

drop table if exists tb_menu;

drop table if exists tb_notice;

drop table if exists tb_operation_log;

drop table if exists tb_position;

drop table if exists tb_role;

drop table if exists tb_role_menu;

drop table if exists tb_salary;

drop table if exists tb_salary_change;

drop table if exists tb_salary_record;

drop table if exists tb_user;

/*==============================================================*/
/* Table: tb_department                                         */
/*==============================================================*/
create table tb_department
(
   dept_id              int not null auto_increment comment '部门id',
   name                 varchar(20) comment '部门名字',
   description          varchar(255) comment '描述',
   pid                  int comment '上级部门id',
   pids                 varchar(255) comment '所有的上级部门id',
   sort                 tinyint comment '排序',
   created_time         datetime comment '创建时间',
   status               tinyint comment '状态',
   version              tinyint,
   primary key (dept_id)
);

alter table tb_department comment '部门表';

/*==============================================================*/
/* Table: tb_dictionary                                         */
/*==============================================================*/
create table tb_dictionary
(
   dict_id              int not null auto_increment,
   code                 tinyint,
   name                 varchar(20),
   is_parent            tinyint,
   pid                  int,
   sort                 tinyint,
   status               tinyint,
   primary key (dict_id)
);

alter table tb_dictionary comment '系统字典表';

/*==============================================================*/
/* Table: tb_employee                                           */
/*==============================================================*/
create table tb_employee
(
   emp_id               varchar(64) not null comment '雇员id',
   dept_id              int comment '部门id',
   position_id          int comment '职位id',
   name                 varchar(20) comment '雇员名字',
   id_card              varchar(50) comment '身份证',
   nation               varchar(20) comment '民族',
   birth                date comment '出生日期',
   hire_date            datetime comment '入职时间',
   fire_date            datetime comment '离职时间',
   address              varchar(100) comment '地址',
   phone                varchar(20) comment '手机号码',
   mail                 varchar(100) comment '邮箱',
   sex                  tinyint comment '性别',
   status               tinyint comment '状态',
   version              int comment '版本',
   primary key (emp_id)
);

alter table tb_employee comment '雇员表';

/*==============================================================*/
/* Table: tb_menu                                               */
/*==============================================================*/
create table tb_menu
(
   menu_id              int not null auto_increment comment '菜单id',
   code                 varchar(20),
   pid                  int comment '父级id',
   pcode                varchar(20),
   pcodes               varchar(255),
   name                 varchar(20),
   icon                 varchar(255),
   url                  varchar(100),
   levels               int,
   is_menu              tinyint,
   is_open              tinyint,
   keep_alive           tinyint,
   component            varchar(20),
   sort                 tinyint,
   status               tinyint,
   primary key (menu_id)
);

alter table tb_menu comment '菜单表，权限表';

/*==============================================================*/
/* Table: tb_notice                                             */
/*==============================================================*/
create table tb_notice
(
   notice_id            varchar(64) not null,
   user_id              varchar(64) comment '系统用户id',
   title                varchar(50),
   content              longtext,
   post_time            datetime,
   status               tinyint,
   primary key (notice_id)
);

alter table tb_notice comment '通知表';

/*==============================================================*/
/* Table: tb_operation_log                                      */
/*==============================================================*/
create table tb_operation_log
(
   oplog_id             varchar(64) not null,
   user_id              varchar(64) comment '系统用户id',
   operate_time         datetime,
   operation            longtext,
   success              tinyint,
   class_name           longtext,
   method_name          varchar(50),
   primary key (oplog_id)
);

alter table tb_operation_log comment '操作日志表';

/*==============================================================*/
/* Table: tb_position                                           */
/*==============================================================*/
create table tb_position
(
   position_id          int not null auto_increment comment '职位id',
   name                 varchar(20) comment '职位名称',
   description          varchar(255),
   created_time         datetime,
   status               tinyint,
   primary key (position_id)
);

alter table tb_position comment '职位表';

/*==============================================================*/
/* Table: tb_role                                               */
/*==============================================================*/
create table tb_role
(
   role_id              int not null auto_increment comment 'id',
   name                 varchar(20) comment '名称',
   pid                  int comment '父级id',
   sort                 int comment '排序',
   status               tinyint comment '状态',
   version              int comment '版本',
   primary key (role_id)
);

alter table tb_role comment '角色表';

/*==============================================================*/
/* Table: tb_role_menu                                          */
/*==============================================================*/
create table tb_role_menu
(
   menu_id              int comment '菜单id',
   role_id              int comment 'id'
);

alter table tb_role_menu comment '角色菜单中间表';

/*==============================================================*/
/* Table: tb_salary                                             */
/*==============================================================*/
create table tb_salary
(
   emp_id               varchar(64) comment '雇员id',
   salary_id            varchar(64) not null,
   basic_salary         decimal(10,2) comment '基础',
   bonus                decimal(10,2) comment '奖金',
   extra_salary         decimal(10,2) comment '加班',
   all_salary           decimal(10,2),
   created_time         datetime,
   updated_time         datetime,
   description          text,
   primary key (salary_id)
);

alter table tb_salary comment '工资详情表';

/*==============================================================*/
/* Table: tb_salary_change                                      */
/*==============================================================*/
create table tb_salary_change
(
   salary_id            varchar(64),
   user_id              varchar(64) comment '系统用户id',
   content              longtext,
   change_time          datetime,
   success              tinyint
);

alter table tb_salary_change comment '修改工资表';

/*==============================================================*/
/* Table: tb_salary_record                                      */
/*==============================================================*/
create table tb_salary_record
(
   record_id            varchar(64) not null,
   emp_id               varchar(64) comment '雇员id',
   salary_id            varchar(64),
   true_salary          decimal(10,2),
   description          text,
   give_time            datetime,
   primary key (record_id)
);

alter table tb_salary_record comment '每次发工资记录表';

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   user_id              varchar(64) not null comment '系统用户id',
   emp_id               varchar(64) comment '雇员id',
   account              varchar(64) comment '账号',
   password             varchar(255) comment '密码',
   phone                varchar(25) comment '电话',
   avatar               varchar(255) comment '头像',
   role_id              varchar(255) comment '角色id',
   social_type          varchar(50),
   social_id            varchar(255),
   status               tinyint comment '状态',
   version              int,
   Column1              varchar(20) comment '预留字段',
   Column2              varchar(20) comment '预留字段',
   primary key (user_id)
);

alter table tb_user comment '系统用户表';


