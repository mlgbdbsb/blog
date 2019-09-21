### 背景介绍

基于SpringBoot + MyBatis + Ajax构建的个人博客项目


### 简单介绍

该项目为一个简易的博客系统，基本需求如下：

+ 用户注册和登录

+ 用户写博客

+ 用户可以对其他用户的博客进行评论，还可以对评论进行回复(回评)

+ 博客对应有相应的标签

+ 管理员可以对后台进行管理，管理内容包括用户管理，文章管理


### 数据库设计

#### 了解需要的实体

从简单介绍中可以了解到需要的实体大体有用户、博客文章、评论、回评、标签，然后对实体属性进行分析，了解用户的属性有什么

+ 用户 	blog_user(username,password,salt,gmt_create,gmt_modified,avatar_url,level,email,gender,personal_signature,is_delete)

	- 用户名					username
	- 密码					password
	- 盐值(与密码加密有关)	salt
	- 注册时间				gmt_create
	- 修改时间				gmt_modified
	- 头像地址				avatar_url
	- 等级					level
	- 邮件					email
	- 性别					gender
	- 个性签名				personal_signature
	- 是否删除 				is_delete

+ 博客 	blog_article(user_id,gmt_create,gmt_modified,title,content,abstract,like_count,view_count,replay_count,category_id,is_publish,is_delete,is_allow_discuss)

	- 发表博客的用户	用户
	- 发表时间		gmt_create
	- 修改时间		gmt_modified
	- 标题 			title
	- 正文内容		content
	- 摘要			abstract
	- 点赞数量		like_count
	- 查看数量		view_count
	- 回复数量		reply_count
	- 关联的标签		标签
	- 是否发表		is_publish
	- 是否删除 		is_delete
	- 是否可评论		is_allow_discuss

+ 评论 	blog_discuss(user_id,gmt_create,gmt_modified,article_id,discuss_content)
	
	- 评论用户		用户
	- 评论时间 		gmt_create
	- 修改时间		gmt_modified
	- 评论的文章		文章
	- 评论内容		discuss_content

+ 回评 	blog_replay_discuss(user_id,article_id,replay_user_id,discuss_content,gmt_create,gmt_modified)

	- 评论用户		用户
	- 评论文章 		文章
	- 回评哪个用户	用户
	- 回评内容		discuss_content
	- 评论时间 		gmt_create
	- 修改时间		gmt_modified

+ 标签 	blog_category(category_name,article_count,gmt_create,gmt_modified)

	- 标签名				category_name
	- 创建时间			gmt_create
	- 修改时间			gmt_modified
	- 该标签下的文章数量	article_count

+ 管理员 	blog_admin(username,password,salt,gmt_create,gmt_modified,operation_time)

	- 用户名			username
	- 密码			password
	- 盐值			salt
	- 用户创建时间	gmt_create
	- 修改时间		gmt_modified
	- 操作时间		operation_time

### 创建数据库

create database blog;

### 创建数据表

+ 用户表

```SQL
CREATE TABLE blog_user(
	id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主键',
	username VARCHAR(50) NOT NULL COMMENT '用户名',
	password CHAR(32) NOT NULL COMMENT '密码',
	salt CHAR(36) NOT NULL COMMENT '盐值，用于加密',
	avatar_url VARCHAR(100) NOT NULL COMMENT '头像地址，需要一个默认值为默认头像',
	level INT NOT NULL COMMENT '用户等级',
	email VARCHAR(50) COMMENT '用户邮箱',
	gender INT NOT NULL COMMENT '用户性别：1-男，0-女',
	personal_signature VARCHAR(255) COMMENT '用户个性签名',
	is_delete INT UNSIGNED NOT NULL COMMENT '是否删除 1-是，2-否',
	gmt_create DATETIME NOT NULL COMMENT '创建时间',
	gmt_modified DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY(id)
) COMMENT '用户表';
```

+ 博客文章表

```SQL
CREATE TABLE blog_article(
	id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主键',
	user_id BIGINT UNSIGNED NOT NULL COMMENT '文章关联的用户即谁发表的',
	title VARCHAR(100) NOT NULL COMMENT '文章标题',
	content TEXT NOT NULL COMMENT '文章内容',
	abstract VARCHAR(255) NOT NULL COMMENT '文章摘要',
	like_count INT UNSIGNED NOT NULL COMMENT '点赞数量',
	view_count INT UNSIGNED NOT NULL COMMENT '查看数量',
	replay_count INT UNSIGNED NOT NULL COMMENT '评论数量',
	article_and_category_id BIGINT UNSIGNED NOT NULL COMMENT '文章下的所有标签',
	is_publish INT UNSIGNED NOT NULL COMMENT '是否已经发表，1-发表，0-未发表',
	is_delete INT UNSIGNED NOT NULL COMMENT '是否删除，1-删除，0-未删除',
	is_allow_discuss INT UNSIGNED NOT NULL COMMENT '是否可评论，1-可以，0-不可以',
	gmt_create DATETIME NOT NULL COMMENT '创建时间',
	gmt_modified DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY(id)
) COMMENT '博客文章表';
```

+ 标签分类

```SQL
CREATE TABLE blog_category(
	id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主键',
	category_name VARCHAR(30) NOT NULL COMMENT '标签名',
	article_count INTEGER UNSIGNED NOT NULL COMMENT '该标签关联的文章',
	gmt_create DATETIME NOT NULL COMMENT '创建时间',
	gmt_modified DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY(id)
) COMMENT '标签表';
```

+ 由于博客和分类是多对多的关系，所以需要多建一张表来表示博客和标签之间的关系

```SQL
CREATE TABLE blog_article_with_category(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	category_id INT UNSIGNED NOT NULL COMMENT '标签id',
	PRIMARY KEY(id)
) COMMENT '标签以及文章对应关系表';
```

+ 评论

```SQL
CREATE TABLE blog_discuss(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	article_id BIGINT UNSIGNED NOT NULL COMMENT '文章id',
	discuss_content VARCHAR(255) NOT NULL COMMENT '评论内容',
	gmt_create DATETIME NOT NULL COMMENT '发表时间',
	gmt_modified DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY(id) 
) COMMENT '评论表';
```

+ 回评

```SQL
CREATE TABLE blog_replay_discuss(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	user_id BIGINT UNSIGNED NOT NULL COMMENT '评论用户id',
	article_id BIGINT UNSIGNED NOT NULL COMMENT '评论文章id',
	replay_user_id BIGINT UNSIGNED NOT NULL COMMENT '被评论用户id',
	discuss_content VARCHAR(255) NOT NULL COMMENT '评论内容',
	gmt_create DATETIME NOT NULL COMMENT '创建时间',
	gmt_modified DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY(id)
) COMMENT '回评表';
```

+ 管理员

```SQL
CREATE TABLE blog_admin(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	username VARCHAR(50) NOT NULL COMMENT '管理员账号',
	password CHAR(32) NOT NULL COMMENT '管理员密码',
	salt CHAR(36) NOT NULL COMMENT '盐值',
	PRIMARY KEY(id)
) COMMENT '管理员表';
```

+ 系统日志表

```SQL
CREATE TABLE blog_sys_log(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	operate VARCHAR(255) NOT NULL COMMENT '进行的操作',
	user_id BIGINT UNSIGNED NOT NULL COMMENT '谁进行的操作',
	username VARCHAR(50) NOT NULL COMMENT '操作人用户名',
	gmt_create DATETIME NOT NULL COMMENT '操作时间',
	gmt_modified DATETIME NOT NULL COMMENT '......',
	PRIMARY KEY(id)
) COMMENT '系统日志表';
```


### 系统集成，需要用到的

SpringBoot + SpringMVC + MyBatis + Swagger + Spring


### 全局配置

1. 数据库连接相关

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tedu_store?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
```

2. 配置数据库执行语句的文件位置

`mybatis.mapper-locations=classpath:mappers/*.xml`

3. 配置返回的json数据为空不显示

`spring.jackson.default-property-inclusion=NON_NULL`

4. 在SpringBoot自动生成的java文件添加如下配置

```java
@Configuration
@MapperScan("org.wang.smartblog.mapper")
```


### 文章摘要生成

由于博客存储的是markdown语法格式的文本，所以需要以下步骤：

+ 将markdown语法格式的文本转成html格式，需要使用到第三方依赖

```xml
<dependency>
	<groupId>com.vladsch.flexmark</groupId>
	<artifactId>flexmark-all</artifactId>
	<version>0.50.40</version>
</dependency>
```
```java
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

private static String markdown2Html(String markdown) {
	MutableDataSet options = new MutableDataSet();
	Parser parser = Parser.builder(options).build();
	HtmlRenderer renderer = HtmlRenderer.builder(options).build();
	Node document = parser.parse(markdown);
	String html = renderer.render(document);
	return html;
}
```

+ 通过正则匹配去除标签并替换，因为标签都是通过`<>`开始和结束的，然后截取部分用于摘要

```java
private static String parseHtml(String html) {
	html = html.replaceAll("<.*?>", "").replaceAll(" ", " ");
	html = html.replaceAll("<.*?", "");
	html = html.replaceAll("\n", " ");
	return (html + "...");
}
```