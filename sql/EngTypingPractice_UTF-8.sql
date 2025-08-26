--DROP TABLE user_table CASCADE CONSTRAINTS;
CREATE TABLE user_table (
    user_no NUMBER PRIMARY KEY,
    user_id VARCHAR2(20) UNIQUE NOT NULL,
    password VARCHAR2(50) NOT NULL,
    nickname VARCHAR2(50) NOT NULL,
    user_type CHAR(1) DEFAULT 'U' CHECK (user_type IN ('A', 'U')),
    create_date DATE DEFAULT SYSDATE,
    status CHAR(1) DEFAULT 'Y' CHECK (status IN ('Y', 'N'))
);

--DROP SEQUENCE short_passage_seq;
CREATE SEQUENCE user_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


-- 초기 데이터 삽입
INSERT INTO USER_TABLE (USER_NO, USER_ID,PASSWORD,NICKNAME, USER_TYPE)
	VALUES (USER_SEQ.NEXTVAL,'1','1','jeyun','A');

INSERT INTO USER_TABLE (USER_NO, USER_ID,PASSWORD,NICKNAME)
	VALUES (USER_SEQ.NEXTVAL,'123','123','jeyun');

------------------------------------------------------------------
--TRUNCATE TABLE WORD_TABLE;
CREATE TABLE WORD_TABLE (
    WORD_NO   NUMBER PRIMARY KEY,
    CONTENT   VARCHAR2(50) UNIQUE NOT NULL,
    WORD_LEN  NUMBER NOT NULL,
    WORD_DIF  CHAR(1) CHECK (WORD_DIF IN ('E', 'M', 'H')),
    STATUS    CHAR(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),
    REGDATE   DATE DEFAULT SYSDATE
);

--DROP SEQUENCE WORD_SEQ;
CREATE SEQUENCE WORD_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

--------------------------------------------------------------------
--DROP TRIGGER TRG_WORD_INSERT_ALL;
CREATE OR REPLACE TRIGGER TRG_WORD_INSERT_ALL
BEFORE INSERT ON WORD_TABLE
FOR EACH ROW
BEGIN
  -- WORD_NO 자동 입력
  :NEW.WORD_NO := WORD_SEQ.NEXTVAL;

  -- 단어 사이즈 자동 저장
  :NEW.WORD_LEN := LENGTH(:NEW.CONTENT);

  -- 단어 난이도 자동 저장
  IF LENGTH(:NEW.CONTENT) <= 4 THEN
    :NEW.WORD_DIF := 'E';
  ELSIF LENGTH(:NEW.CONTENT) <= 6 THEN
    :NEW.WORD_DIF := 'M';
  ELSE
    :NEW.WORD_DIF := 'H';
  END IF;
END;
/

CREATE OR REPLACE TRIGGER TRG_UPDATE_WORD_ALL
BEFORE UPDATE ON WORD_TABLE
FOR EACH ROW
BEGIN
  :NEW.WORD_LEN := LENGTH(:NEW.CONTENT);

  IF LENGTH(:NEW.CONTENT) <= 4 THEN
    :NEW.WORD_DIF := 'E';
  ELSIF LENGTH(:NEW.CONTENT) <= 6 THEN
    :NEW.WORD_DIF := 'M';
  ELSE
    :NEW.WORD_DIF := 'H';
  END IF;
END;
/

--------------------------------------------------------------------
INSERT INTO WORD_TABLE (CONTENT) VALUES ('airport');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('airplane');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('apple');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('backpack');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('bag');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('banana');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('battery');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('bat');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('blanket');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('book');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('box');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('bread');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('brush');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('calendar');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('cap');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('carpet');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('chair');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('chocolate');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('classroom');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('cloud');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('coffee');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('cup');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('dictionary');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('dinosaur');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('dog');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('egg');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('elephant');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('exercise');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('flashlight');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('glass');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('grape');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('hat');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('headphones');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('holiday');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('hospital');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('juice');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('keyboard');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('language');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('map');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('microscope');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('milk');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('monitor');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('mountain');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('motorcycle');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('mouse');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('newspaper');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('notebook');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('office');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('orange');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('pen');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('pencil');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('pet');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('phone');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('picture');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('piano');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('planet');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('plant');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('president');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('printer');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('refrigerator');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('run');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('sandcastle');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('sandwich');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('school');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('shirt');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('shoe');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('shoulder');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('silver');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('skateboard');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('snow');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('socks');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('spoon');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('sun');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('table');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('technology');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('telephone');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('toothpaste');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('toy');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('tree');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('umbrella');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('universe');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('university');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('watch');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('watermelon');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('yellow');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('air');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('camera');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('car');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('child');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('city');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('clock');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('computer');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('day');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('desk');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('door');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('dream');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('eye');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('face');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('family');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('fire');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('floor');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('food');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('friend');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('game');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('hand');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('house');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('job');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('key');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('laptop');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('life');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('light');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('love');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('man');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('money');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('movie');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('music');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('news');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('road');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('screen');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('sleep');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('sound');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('story');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('time');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('water');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('window');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('woman');
INSERT INTO WORD_TABLE (CONTENT) VALUES ('world');

--------------------------------------------------------------------
-- 짧은 글 연습 컨텐츠 저장 테이블
CREATE TABLE SHORT_SENTENCE_TABLE (
    SENTENCE_NO    NUMBER PRIMARY KEY,
    CONTENT       VARCHAR2(1000) NOT NULL,
    CHAR_LENGTH   NUMBER NOT NULL,
    STATUS        CHAR(1) DEFAULT 'Y' CHECK (STATUS IN ('Y','N')),
    REGDATE       DATE DEFAULT SYSDATE
);

CREATE SEQUENCE SHORT_SENTENCE_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE OR REPLACE TRIGGER TRG_SHORT_SENTENCE_INSERT
BEFORE INSERT ON SHORT_SENTENCE_TABLE
FOR EACH ROW
BEGIN
  :NEW.SENTENCE_NO := SHORT_SENTENCE_SEQ.NEXTVAL;
  :NEW.CHAR_LENGTH := LENGTH(:NEW.CONTENT);
END;
/

CREATE OR REPLACE TRIGGER TRG_UPDATE_SHORT_SENTENCE
BEFORE UPDATE ON SHORT_SENTENCE_TABLE
FOR EACH ROW
BEGIN
  :NEW.CHAR_LENGTH := LENGTH(:NEW.CONTENT);
END;
/

--------------------------------------------------------------------
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('The quick brown fox jumps over the lazy dog.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Typing every day will improve your speed.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Practice makes perfect.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Always check your spelling before submitting.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('I love learning new things through code.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Java is a powerful and versatile language.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Keep your fingers on the home row.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Accuracy is more important than speed.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Focus on consistency over time.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Short sentences help you type faster.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Never give up, even when it is hard.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Type this sentence as fast as you can.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('This is a sample text for typing practice.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Correct mistakes quickly and move on.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Daily typing will strengthen muscle memory.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Start slow and build your rhythm.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Typing is a valuable skill in the digital age.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Learn to type without looking at the keyboard.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Use all your fingers for better efficiency.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Typing games can make practice more fun.');

INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Reading books daily helps expand your vocabulary.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Stay calm and focused while typing.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Try to type each sentence with zero mistakes.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Speed comes with time and regular practice.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('It is better to be accurate than to be fast.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Set daily goals to track your typing progress.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Warm up your fingers before you begin typing.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Use online tools to measure your typing speed.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Consistency is the key to mastery.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Avoid looking at the keyboard while typing.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Type the same sentence several times to improve.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Focus on one finger at a time to fix habits.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Typing with good posture helps reduce fatigue.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('You can type faster when you relax your hands.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Track your progress using a typing journal.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Challenge yourself to beat your last score.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Avoid distractions while practicing typing.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Use simple sentences to begin your training.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Typing becomes easier with repetition.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Enjoy the process of learning to type better.');

INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Practice typing until it feels natural.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Keep your eyes on the screen, not the keys.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Good posture helps you type for longer.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Quick thinking leads to quick fingers.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Typing is like playing a musical instrument.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Take short breaks to rest your hands.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Use rhythm and flow to type smoothly.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Your typing will improve with each session.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Small steps lead to big progress.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Confidence grows as you master typing.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Make typing part of your daily routine.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('The best way to learn is by doing it often.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Mistakes are part of the learning process.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Great typists type with focus and control.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Start slow, then build up your pace.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Accuracy first, speed second.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Let your hands learn the keyboard layout.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Don''t rush steady typing wins.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Finger placement matters more than speed.');
INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES ('Repeating sentences builds memory and skill.');
----------------------------------------------------------------------------------------------------------
--DROP TABLE LONG_SENTENCE_TABLE CASCADE CONSTRAINTS;
CREATE TABLE LONG_SENTENCE_TABLE (
    SENTENCE_NO   NUMBER PRIMARY KEY,
    TITLE         VARCHAR2(100) UNIQUE NOT NULL,
    CONTENT       CLOB NOT NULL,
    CHAR_LENGTH   NUMBER NOT NULL,
    LINE_COUNT    NUMBER NOT NULL,
    STATUS        CHAR(1) DEFAULT 'Y' CHECK (STATUS IN ('Y','N')),
    REGDATE       DATE DEFAULT SYSDATE
);

--DROP SEQUENCE LONG_SENTENCE_SEQ;
CREATE SEQUENCE LONG_SENTENCE_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE OR REPLACE TRIGGER TRG_INSERT_LONG_SENTENCE
BEFORE INSERT ON LONG_SENTENCE_TABLE
FOR EACH ROW
BEGIN
  :NEW.SENTENCE_NO := LONG_SENTENCE_SEQ.NEXTVAL;
  :NEW.CHAR_LENGTH := LENGTH(:NEW.CONTENT);
  :NEW.LINE_COUNT := REGEXP_COUNT(:NEW.CONTENT, CHR(13) || CHR(10)) + 1;
END;
/

CREATE OR REPLACE TRIGGER TRG_UPDATE_LONG_SENTENCE
BEFORE UPDATE ON LONG_SENTENCE_TABLE
FOR EACH ROW
BEGIN
  :NEW.CHAR_LENGTH := LENGTH(:NEW.CONTENT);
  :NEW.LINE_COUNT := REGEXP_COUNT(:NEW.CONTENT, CHR(13) || CHR(10)) + 1;
END;
/

----------------------------------------------------------------------------------------------------------

INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT)
VALUES (
  'Typing Practice Basics',
  'Typing is a useful skill for many people.' || CHR(13) || CHR(10) ||
  'It helps you work faster and more accurately.' || CHR(13) || CHR(10) ||
  'Start by practicing a few minutes every day.'
);

INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT)
VALUES ('The Beauty of Nature',
        'Nature is full of wonders.' || CHR(13) || CHR(10) ||
        'From the tallest mountains to the deepest oceans,' || CHR(13) || CHR(10) ||
        'there is beauty everywhere.' || CHR(13) || CHR(10) ||
        'We must protect and cherish our environment.');

INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT)
VALUES ('My Morning Routine',
        'I wake up at six every morning.' || CHR(13) || CHR(10) ||
        'After brushing my teeth, I go for a walk.' || CHR(13) || CHR(10) ||
        'Then I make breakfast and read the news.' || CHR(13) || CHR(10) ||
        'Starting the day calmly helps me focus.');

INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT)
VALUES ('Learning a New Language',
        'Learning a new language is both challenging and rewarding.' || CHR(13) || CHR(10) ||
        'It helps you connect with more people and understand new cultures.' || CHR(13) || CHR(10) ||
        'Daily practice is the key to improvement.' || CHR(13) || CHR(10) ||
        'Don''t be afraid of making mistakes?they are part of the journey!');

INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT)
VALUES ('A Walk in the Park',
        'The sun was shining brightly today.' || CHR(13) || CHR(10) ||
        'I decided to take a walk in the nearby park.' || CHR(13) || CHR(10) ||
        'Birds were singing, and children were playing.' || CHR(13) || CHR(10) ||
        'It was a peaceful and refreshing experience.');

INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT)
VALUES ('Benefits of Reading',
        'Reading expands your knowledge and imagination.' || CHR(13) || CHR(10) ||
        'It helps improve vocabulary and communication skills.' || CHR(13) || CHR(10) ||
        'Books can take you to different places and times.' || CHR(13) || CHR(10) ||
        'Make time to read every day.');

INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT)
VALUES ('The Importance of Sleep',
        'Getting enough sleep is vital for your health.' || CHR(13) || CHR(10) ||
        'It allows your body to rest and your mind to reset.' || CHR(13) || CHR(10) ||
        'Lack of sleep affects mood and concentration.' || CHR(13) || CHR(10) ||
        'Aim for seven to eight hours each night.');

INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT)
VALUES ('My Favorite Season',
        'Spring is my favorite season of the year.' || CHR(13) || CHR(10) ||
        'The flowers start to bloom and the weather warms up.' || CHR(13) || CHR(10) ||
        'People go outside more and enjoy nature.' || CHR(13) || CHR(10) ||
        'It feels like everything is coming back to life.');

INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT)
VALUES ('Healthy Eating Habits',
        'Eating healthy is important for a strong body.' || CHR(13) || CHR(10) ||
        'Include fruits, vegetables, and whole grains in your meals.' || CHR(13) || CHR(10) ||
        'Avoid too much sugar and processed food.' || CHR(13) || CHR(10) ||
        'Drink enough water and eat at regular times.');

----------------------------------------------------------------------------------------------------------
-- 아래는 로그 저장 테이블 생성
--DROP TABLE WORD_LOG_TABLE CASCADE CONSTRAINTS;
CREATE TABLE WORD_LOG_TABLE (
    LOG_NO         NUMBER PRIMARY KEY,
    USER_NO        NUMBER REFERENCES USER_TABLE(USER_NO),
    WORD_COUNT     NUMBER NOT NULL,
    CORRECT_CNT    NUMBER NOT NULL,
    INCORRECT_CNT  NUMBER NOT NULL,
    ACCURACY       NUMBER(5,2),
    REGDATE        DATE
);

CREATE SEQUENCE WORD_LOG_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE OR REPLACE TRIGGER TRG_INSERT_WORD_LOG
BEFORE INSERT ON WORD_LOG_TABLE
FOR EACH ROW
BEGIN
  :NEW.LOG_NO := WORD_LOG_SEQ.NEXTVAL;
  :NEW.ACCURACY := ROUND(:NEW.CORRECT_CNT * 100.0 / NULLIF(:NEW.WORD_COUNT, 0), 2);
  :NEW.REGDATE := SYSDATE;
END;
/


--DROP TABLE SHORT_SENTENCE_LOG_TABLE CASCADE CONSTRAINTS;
CREATE TABLE SHORT_SENTENCE_LOG_TABLE (
    LOG_NO         NUMBER PRIMARY KEY,
    USER_NO        NUMBER REFERENCES USER_TABLE(USER_NO),
    SENT_COUNT     NUMBER NOT NULL,      
    CHAR_COUNT     NUMBER NOT NULL,      
    CORRECT_COUNT  NUMBER NOT NULL,      
    ACCURACY       NUMBER(5,2) NOT NULL,
    TOTAL_TIME     NUMBER(6,2) NOT NULL, 
    AVG_SPEED      NUMBER NOT NULL,      
    MAX_SPEED      NUMBER NOT NULL,
    REGDATE        DATE
);

--DROP SEQUENCE SHORT_SENTENCE_LOG_SEQ;
CREATE SEQUENCE SHORT_SENTENCE_LOG_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE OR REPLACE TRIGGER TRG_INSERT_SHORT_LOG
BEFORE INSERT ON SHORT_SENTENCE_LOG_TABLE
FOR EACH ROW
BEGIN
  :NEW.LOG_NO := SHORT_SENTENCE_LOG_SEQ.NEXTVAL;
  :NEW.REGDATE := SYSDATE;
END;
/



CREATE TABLE LONG_SENTENCE_LOG_TABLE (
    LOG_NO          NUMBER PRIMARY KEY,
    USER_NO         NUMBER NOT NULL,
    SENTENCE_NO     NUMBER NOT NULL,
    LINE_COUNT      NUMBER NOT NULL,
    CHAR_COUNT      NUMBER NOT NULL,
    CORRECT_COUNT   NUMBER NOT NULL,
    ACCURACY        NUMBER(5,2) NOT NULL,
    TOTAL_TIME      NUMBER(6,2) NOT NULL,
    AVG_SPEED       NUMBER NOT NULL,
    REGDATE         DATE,

    CONSTRAINT FK_LONG_LOG_USER 
        FOREIGN KEY (USER_NO) REFERENCES USER_TABLE(USER_NO),
    
    CONSTRAINT FK_LONG_LOG_SENTENCE 
        FOREIGN KEY (SENTENCE_NO) REFERENCES LONG_SENTENCE_TABLE(SENTENCE_NO)
);

CREATE SEQUENCE LONG_SENTENCE_LOG_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE OR REPLACE TRIGGER TRG_INSERT_LONG_LOG
BEFORE INSERT ON LONG_SENTENCE_LOG_TABLE
FOR EACH ROW
BEGIN
  :NEW.LOG_NO := LONG_SENTENCE_LOG_SEQ.NEXTVAL;
  :NEW.REGDATE := SYSDATE;
END;
/
----------------------------------------------------------------------------------------------------------
-- 로그 테이블에 대해서 제약조건을 설정하는 쿼리.
--ALTER TABLE WORD_LOG_TABLE
--ADD CONSTRAINT FK_WORD_LOG_USER
--FOREIGN KEY (USER_NO)
--REFERENCES USER_TABLE(USER_NO);

--ALTER TABLE SHORT_SENTENCE_LOG_TABLE
--ADD CONSTRAINT FK_SHORT_LOG_USER
--FOREIGN KEY (USER_NO)
--REFERENCES USER_TABLE(USER_NO);
--실행해보니 이미 존재함.

