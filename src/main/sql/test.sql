SELECT cn_user_id from cn_user WHERE cn_user_name="demo";

SELECT cn_notebook_id from cn_notebook WHERE cn_user_id="48595f52-b22c-4485-9244-f4004255b972";

SELECT cn_note_title,cn_notebook_id FROM cn_note WHERE cn_notebook_id is NOT  NULL ;

/*查找有笔记的笔记本*/
/*1.子查询*/
SELECT DISTINCT cn_notebook_name FROM cn_notebook AS notebook
WHERE EXISTS (
SELECT cn_note_id from cn_note AS note WHERE note.cn_notebook_id=notebook.cn_notebook_id
)
/*2.关联查询*/
SELECT DISTINCT cn_notebook_name,cn_note_title FROM cn_notebook AS notebook
LEFT JOIN cn_note AS note ON notebook.cn_notebook_id=note.cn_notebook_id

SELECT cn_note_id FROM cn_note;

SELECT * FROM cn_note WHERE cn_note_title="实验添加笔记";
/*查找有笔记本的userId以及notebookId*/

SELECT user.cn_user_id,cn_notebook_id,cn_user_name
FROM cn_notebook AS notebook
  JOIN cn_user AS user ON notebook.cn_user_id=user.cn_user_id
WHERE cn_notebook_id IS NOT NULL


SELECT note.cn_note_id,notebook.cn_notebook_id,notebook.cn_notebook_name
FROM cn_notebook AS notebook
  JOIN cn_note AS note ON notebook.cn_notebook_id=note.cn_notebook_id;

/*查询用户以及用户的的笔记本名称以及笔记名称*/
SELECT user.cn_user_id ,cn_user_name ,notebook1.cn_notebook_name,notebook1.cn_note_title,
      notebook1.cn_notebook_id,notebook1.cn_note_id
FROM cn_user AS user
  JOIN( SELECT note.cn_note_title,notebook.cn_notebook_id,notebook.cn_notebook_name,
                notebook.cn_user_id,note.cn_note_id
        FROM cn_notebook AS notebook
        JOIN cn_note AS note
        ON notebook.cn_notebook_id=note.cn_notebook_id) AS notebook1
ON user.cn_user_id=notebook1.cn_user_id;


SELECT * FROM cn_note WHERE cn_notebook_id is NULL


/*测试sql语句的执行顺序*/
create table cn_test(
  id INT PRIMARY KEY  AUTO_INCREMENT,
  age INT(10)
);
INSERT INTO cn_test(age) VALUES (10);
INSERT INTO cn_test(age) VALUES (20);
INSERT INTO cn_test(age) VALUES (30);
INSERT INTO cn_test(age) VALUES (40);
INSERT INTO cn_test(age) VALUES (50);
INSERT INTO cn_test(age) VALUES (60);
INSERT INTO cn_test(age) VALUES (70);
INSERT INTO cn_test(age) VALUES (80);
INSERT INTO cn_test(age) VALUES (90);
INSERT INTO cn_test(age) VALUES (100);
INSERT INTO cn_test(age) VALUES (200);
SELECT * FROM cn_test;


SELECT avg(age) from cn_test;
SELECT * FROM cn_test WHERE age >(SELECT AVG(age) FROM cn_test);
SELECT * FROM cn_test GROUP BY age HAVING avg(age)>10;
SELECT max(age) FROM cn_test;

CREATE TABLE  cn_star(
  cn_star VARCHAR(50) PRIMARY KEY ,
  cn_user_id VARCHAR(50),
  cn_stars INT
);
ALTER TABLE cn_star CHANGE cn_star cn_star_id VARCHAR(50);
DESC cn_star;

SELECT  * FROM cn_star;
DELETE FROM cn_star WHERE cn_star.cn_star_id="42e15836-1a28-4a3f-96f7-9d08fb619f47";

//测试是排序后分页查询

SELECT * FROM cn_notebook ORDER BY cn_notebook_createtime DESC LIMIT 0,10;

//


