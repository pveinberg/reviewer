--------------------------------------------------------------------------------
--									DDL SCRIPT							      --
--------------------------------------------------------------------------------


-- --------------------------------  SCHEMAS -------------------------------- --

CREATE SCHEMA reviewer AUTHORIZATION sa;


-- --------------------------------  TABLES --------------------------------- --

CREATE TABLE reviewer.literature_reviews(
	literature_review_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
	title VARCHAR(5000)
);

CREATE TABLE reviewer.studies(
	study_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
	code VARCHAR(1000),
	title VARCHAR(5000),
	status VARCHAR(100),
	source VARCHAR(1000),
	abstract VARCHAR(50000),
	year VARCHAR(4),
	url VARCHAR(10000),
);

CREATE TABLE reviewer.studies_authors(
	study_id INTEGER,
	author VARCHAR(10000),
	CONSTRAINT authors_studies_fk FOREIGN KEY(study_id) REFERENCES reviewer.studies(study_id)
);

CREATE TABLE reviewer.studies_institutions(
	study_id INTEGER,
	institution VARCHAR(10000),
	CONSTRAINT institutions_studies_fk FOREIGN KEY(study_id) REFERENCES reviewer.studies(study_id)
);

CREATE TABLE reviewer.studies_countries(
	study_id INTEGER,
	country VARCHAR(10000),
	CONSTRAINT countries_studies_fk FOREIGN KEY(study_id) REFERENCES reviewer.studies(study_id)
);

CREATE TABLE reviewer.literature_review_studies(
	study_id INTEGER,
	literature_review_id INTEGER,
	CONSTRAINT lrs_studies_fk FOREIGN KEY(study_id) REFERENCES reviewer.studies(study_id),
	CONSTRAINT lrs_literature_review_fk FOREIGN KEY(literature_review_id) REFERENCES reviewer.literature_reviews(literature_review_id)
);

CREATE TABLE reviewer.literature_reviews_sources(
	literature_review_id INTEGER,
	name VARCHAR(1000),
	type VARCHAR(100),
	total_found INTEGER,
	total_fetched INTEGER,	
	CONSTRAINT lrs_sources_fk FOREIGN KEY(literature_review_id) REFERENCES reviewer.literature_reviews(literature_review_id),
);
