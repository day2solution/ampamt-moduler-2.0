-- SELECT
    -- 'DROP TABLE '
    -- || table_name
    -- || ';'
-- FROM
    -- user_tables
-- WHERE
    -- table_name NOT IN ( 'AMPAMT_ACCOUNT', 'DOCTORS_DOCUMENTS_DETAILS', 'DOCTORS_ACCOUNT_DETAILS', 'BUSINESS_ACCOUNT_DETAILS','BUSINESS_DOCUMENTS_DETAILS','TBL_STATE','TBL_CITY' );
--delete from ampamt_transactions;
--delete from ampamt_cash_account;
--delete from doctors_documents_details;
--delete from doctors_account_details;
--delete from business_account_details where id='AC20200911132506480';
--delete from business_documents_details where id='AC20200911132506480';
--delete from ampamt_events;
--delete from ampamt_account where id='AC20200911132506480';


pass detail= key =6 text=ampamt
CREATE USER ampamt IDENTIFIED BY "6tAyL1hqrHEHruDtsiYS7A==";
GRANT ALL PRIVILEGES TO ampamt;

-------------------

CREATE TABLE ampamt_account (
    id            VARCHAR2(25 CHAR) primary key,
    username      VARCHAR2(50 CHAR),
    password      VARCHAR2(100 CHAR),
    email_id      VARCHAR2(50 CHAR),
    active_flag   VARCHAR2(1 CHAR),
    status        VARCHAR2(20),
	contact_no varchar2(10 char),
    account_type varchar2(10 char)
);


CREATE TABLE doctors_account_details (
    id                VARCHAR2(25 CHAR) PRIMARY KEY,
    first_name                VARCHAR2(100 CHAR),
	last_name 				  varchar2(50 char);
	middle_name 			  varchar2(50 char);
    gender                    VARCHAR2(1 CHAR),
    dob                       DATE,
    blood_group               VARCHAR2(5 CHAR),
    marital_status            VARCHAR2(10),
    practicing_as             VARCHAR2(20 CHAR),
    practicing                VARCHAR2(4000 CHAR),
	 city                     VARCHAR2(100 CHAR),
    state                    VARCHAR2(100 CHAR),
    practice_city             VARCHAR2(30 CHAR),
    nationality               VARCHAR2(30 CHAR),
    religion                  VARCHAR2(30 CHAR),
    father_name               VARCHAR2(100 CHAR),
    mother_name               VARCHAR2(100 CHAR),
	wife_husband_name 		  varchar2(100 char),
    company_name              VARCHAR2(100 CHAR),
    experienc_establishment   varchar2(8 char),
    regist_office_add         VARCHAR2(200 CHAR),
    contact_no                VARCHAR2(10 CHAR),
    other_contact_no          VARCHAR2(10 CHAR),
    whatsapp_no               VARCHAR2(10 CHAR),
    emergency_no              VARCHAR2(10 CHAR),
    email_id                  VARCHAR2(50 CHAR),
    aadhar_no                 VARCHAR2(12 CHAR),
    pan_no                    VARCHAR2(10 CHAR),
    passport_no               VARCHAR2(20 CHAR),
    tnc_accepted              VARCHAR2(1 CHAR),
    active_flag               VARCHAR2(1 CHAR),
    status                    VARCHAR2(20),
    account_type              VARCHAR2(10 CHAR),
    create_date               DATE,
	name_title 				  varchar2(10 char),
	admin_flag varchar2(1 char),
	reference varchar2(100 char);
    FOREIGN KEY ( id )
        REFERENCES ampamt_account ( id )
);
alter table doctors_account_details add  have_ramp varchar2(1 char);
alter table doctors_account_details add  apply_ramp varchar2(1 char);
alter table doctors_account_details add country varchar2(150 char);

CREATE TABLE doctors_documents_details (
    document_id              VARCHAR2(20 CHAR) PRIMARY KEY,
    id                       VARCHAR2(25 CHAR) NOT NULL,
    profile_pic              VARCHAR2(1 CHAR),
    quali_certificate1       VARCHAR2(1 CHAR),
    quali_certificate2       VARCHAR2(1 CHAR),
    quali_certificate3       VARCHAR2(1 CHAR),
    quali_certificate4       VARCHAR2(1 CHAR),
    quali_certificate5       VARCHAR2(1 CHAR),
    quali_certificate6       VARCHAR2(1 CHAR),
    ramp_certificate         VARCHAR2(1 CHAR),
    prof_certificate         VARCHAR2(1 CHAR),
	medi_council_registration varchar2(1 char),
    health_dep_certificate   VARCHAR2(1 CHAR),
    ayush_dep_registration   VARCHAR2(1 CHAR),
    other_lic_certificate1   VARCHAR2(1 CHAR),
    other_lic_certificate2   VARCHAR2(1 CHAR),
    other_lic_certificate3   VARCHAR2(1 CHAR),
    professional_workimg1    VARCHAR2(1 CHAR),
    professional_workimg2    VARCHAR2(1 CHAR),
    professional_workimg3    VARCHAR2(1 CHAR),
    website_link             VARCHAR2(200 CHAR),
    facebook_link            VARCHAR2(200 CHAR),
    instagram_link           VARCHAR2(200 CHAR),
    youtube_link             VARCHAR2(200 CHAR),
    twitter_link             VARCHAR2(200 CHAR),
    experience_comments      VARCHAR2(500 CHAR),
    achievements_comments    VARCHAR2(250 CHAR),
    FOREIGN KEY ( id )
        REFERENCES ampamt_account ( id )
);
alter table doctors_documents_details add aadhar varchar2(3 char);
alter table doctors_documents_details add aadhar_back varchar2(3 char);
alter table doctors_documents_details add panBack varchar2(3 char);
alter table doctors_documents_details add panFront varchar2(3 char);


CREATE TABLE business_documents_details (
    document_id             VARCHAR2(25 CHAR) PRIMARY KEY,
    id                      VARCHAR2(25 CHAR),
    company_logo            VARCHAR2(1 CHAR),
    msme_certificate        VARCHAR2(1 CHAR),
    company_pan             VARCHAR2(1 CHAR),
    company_aadhar          VARCHAR2(1 CHAR),
    COMPANY_AADHAR_BACK 	varchar2(1 char);
    gumasta_certificate     VARCHAR2(1 CHAR),
    iso_certificate         VARCHAR2(1 CHAR),
    gst_certificate         VARCHAR2(1 CHAR),
    compliance_certificate  VARCHAR2(1 CHAR),
    quali_certificate1      VARCHAR2(1 CHAR),
    quali_certificate2      VARCHAR2(1 CHAR),
    quali_certificate3      VARCHAR2(1 CHAR),
    quali_certificate4      VARCHAR2(1 CHAR),
    quali_certificate5      VARCHAR2(1 CHAR),
    quali_certificate6      VARCHAR2(1 CHAR),
    prof_certificate        VARCHAR2(1 CHAR),
    other_lic_certificate1  VARCHAR2(1 CHAR),
    other_lic_certificate2  VARCHAR2(1 CHAR),
    other_lic_certificate3  VARCHAR2(1 CHAR),
    professional_workimg1   VARCHAR2(1 CHAR),
    professional_workimg2   VARCHAR2(1 CHAR),
    professional_workimg3   VARCHAR2(1 CHAR),
    website_link            VARCHAR2(200 CHAR),
    facebook_link           VARCHAR2(200 CHAR),
    instagram_link          VARCHAR2(200 CHAR),
    youtube_link            VARCHAR2(200 CHAR),
    twitter_link            VARCHAR2(200 CHAR),
    about_comapny           VARCHAR2(500 CHAR),
    detail_about_product    VARCHAR2(250 CHAR),
    FOREIGN KEY ( id )
        REFERENCES ampamt_account ( id )
);
CREATE TABLE business_account_details (
    id                       VARCHAR2(25 CHAR) PRIMARY KEY,
    name                     VARCHAR2(100 CHAR),
    company_owner_name       VARCHAR2(100 CHAR),
    owner_gender             VARCHAR2(1 CHAR),
    representative_name      VARCHAR2(100 CHAR),
    representative_gender    VARCHAR2(1 CHAR),
    owner_nationality        VARCHAR2(30 CHAR),
    owner_religion           VARCHAR2(30 CHAR),
    owner_detail             VARCHAR2(100 CHAR),
    sector                   VARCHAR2(50 CHAR),
   EXPERIENC_ESTABLISHMENT varchar2(8 char),
    regist_office_add        VARCHAR2(200 CHAR),
    landmark                 VARCHAR2(30 CHAR),
    city                     VARCHAR2(30 CHAR),
    state                    VARCHAR2(30 CHAR),
    zip_code                 VARCHAR2(30 CHAR),
    contact_no               VARCHAR2(10 CHAR),
    landline_no              VARCHAR2(10 CHAR),
    other_contact_no         VARCHAR2(10 CHAR),
    whatsapp_no              VARCHAR2(10 CHAR),
    email_id                 VARCHAR2(50 CHAR),
    other_email_id           VARCHAR2(50 CHAR),
    aadhar_no                VARCHAR2(12 CHAR),
    pan_no                   VARCHAR2(10 CHAR),
    passport_no              VARCHAR2(20 CHAR),
    tnc_accepted             VARCHAR2(1 CHAR),
    active_flag              VARCHAR2(1 CHAR),
    status                   VARCHAR2(20),
    account_type             VARCHAR2(10 CHAR),
    create_date              DATE,
	owner_name_title 		varchar2(10 char),
	REPRESENTATIVE_NAME_TITLE  varchar2(10 char),
	admin_flag varchar2(1 char)
    FOREIGN KEY ( id )
        REFERENCES ampamt_account ( id )
);
ALTER TABLE business_account_details ADD SERVICE_STATES VARCHAR2(150 CHAR);
ALTER TABLE business_account_details ADD SERVICE_CITIES VARCHAR2(4000 char);
CREATE TABLE ampamt_events (
    event_id           VARCHAR2(25 CHAR) PRIMARY KEY,
    account_id         VARCHAR2(25 CHAR),
    event_img          VARCHAR2(1 CHAR),
    event_title        VARCHAR2(50 CHAR),
    event_description  VARCHAR2(100 CHAR),
    event_state        VARCHAR2(30 CHAR),
    event_city         VARCHAR2(30 CHAR),
    FOREIGN KEY ( account_id )
        REFERENCES ampamt_account ( id )
);

CREATE TABLE ampamt_transactions (
    transaction_id    VARCHAR2(25 CHAR) PRIMARY KEY,
    cash_account_id   VARCHAR2(25 CHAR),
    account_id        VARCHAR2(25 CHAR),
    paid_flag         VARCHAR2(1 CHAR),
    amount            NUMBER(10, 2),
    create_date       DATE,
    transaction_date  DATE,
    status            VARCHAR2(10 CHAR),
    remarks           VARCHAR2(100 CHAR),
    payment_method    VARCHAR2(20 CHAR),
	extra    		  VARCHAR2(200 CHAR),
    FOREIGN KEY ( cash_account_id )
        REFERENCES ampamt_cash_account ( cash_account_id )
);
alter table ampamt_transactions add  payment_id varchar2(100 char);
alter table ampamt_transactions add  order_id varchar2(100 char);
alter table ampamt_transactions add  signature varchar2(100 char);
alter table ampamt_transactions add  update_date date;

CREATE TABLE ampamt_cash_account (
    cash_account_id   VARCHAR2(25 CHAR) PRIMARY KEY,
    account_id        VARCHAR2(25 CHAR),
    updated_by        VARCHAR2(25 CHAR),
    available_amount  NUMBER(10, 2),
    create_date       DATE,
    update_date       DATE,
    FOREIGN KEY ( account_id )
        REFERENCES ampamt_account ( id )
);
CREATE TABLE ampamt_therapies (
    therapy_id    VARCHAR2(20 CHAR) PRIMARY KEY,
    therapy_name  VARCHAR2(100 CHAR),
    active_flag   VARCHAR2(1 CHAR)
);
CREATE TABLE advertisements (
    adv_id        VARCHAR2(25 CHAR) PRIMARY KEY,
    account_id    VARCHAR2(25 CHAR),
    create_date   DATE,
    display_flag  VARCHAR2(1 CHAR),
    image_name    VARCHAR2(50 CHAR),
    FOREIGN KEY ( account_id )
        REFERENCES ampamt_account ( id )
);

CREATE TABLE ampamt_audit_trail (
    id          VARCHAR2(30 CHAR) PRIMARY KEY,
    account_id  VARCHAR2(30 CHAR),
    action      VARCHAR2(1000 CHAR),
    server_ip   VARCHAR2(50 CHAR),
    client_ip   VARCHAR2(50 CHAR),
    audit_date  DATE,
    module      VARCHAR2(50 CHAR)
);

CREATE TABLE tbl_state (
    state_code     VARCHAR2(10 CHAR) PRIMARY KEY,
    state_name     VARCHAR2(100 CHAR),
    state_country  VARCHAR2(100 CHAR)
);

CREATE TABLE tbl_city (
    CITY_CODE     VARCHAR2(10 CHAR) PRIMARY KEY,
    STATE_CODE     VARCHAR2(100 CHAR),
    CITY_NAME  VARCHAR2(100 CHAR),
    FOREIGN KEY ( STATE_CODE )
        REFERENCES tbl_state ( STATE_CODE )
);