    CREATE USER 'ampamt'@'%' IDENTIFIED BY '6tAyL1hqrHEHruDtsiYS7A==';

    GRANT ALL PRIVILEGES ON ampamt_db.* TO 'ampamt'@'%' WITH GRANT OPTION;

    FLUSH PRIVILEGES;

    EXIT;
	
	 DROP USER 'ampamt'@'%';
	 
create table ampamt_account (
    id            varchar(25 ) primary key,
    username      varchar(50 ),
    password      varchar(100 ),
    email_id      varchar(50 ),
    active_flag   varchar(1 ),
    status        varchar(20),
	contact_no varchar(10 ),
    account_type varchar(10 ),
	OTP	varchar(8 )
);


create table doctors_account_details (
    id                varchar(25 ) primary key,
    first_name                varchar(100 ),
	last_name 				  varchar(50 ),
	middle_name 			  varchar(50 ),
    gender                    varchar(1 ),
    dob                       date,
    blood_group               varchar(5 ),
    marital_status            varchar(10),
    practicing_as             varchar(20 ),
    practicing                varchar(4000 ),
	 city                     varchar(100 ),
    state                    varchar(100 ),
    practice_city             varchar(30 ),
    nationality               varchar(30 ),
    religion                  varchar(30 ),
    father_name               varchar(100 ),
    mother_name               varchar(100 ),
	wife_husband_name 		  varchar(100 ),
    company_name              varchar(100 ),
	company_title 				varchar(30 ),
    experienc_establishment   varchar(8 ),
    regist_office_add         varchar(200 ),
    contact_no                varchar(10 ),
    other_contact_no          varchar(10 ),
    whatsapp_no               varchar(10 ),
    emergency_no              varchar(10 ),
    email_id                  varchar(50 ),
    aadhar_no                 varchar(12 ),
    pan_no                    varchar(10 ),
    passport_no               varchar(20 ),
    tnc_accepted              varchar(1 ),
    active_flag               varchar(1 ),
    status                    varchar(20),
    account_type              varchar(10 ),
    create_date               date,
	name_title 				  varchar(10 ),
	profile_pic 				varchar(30 ),
	permanent_address varchar(500 ),
	zip_code varchar(8 ),
	admin_flag varchar(1 ),
	have_ramp varchar(1 ),
	reference varchar(100 ),
	apply_ramp varchar(1 ),
	country varchar(150 ),
    foreign key ( id )
        references ampamt_account ( id )
);


create table doctors_documents_details (
    document_id              varchar(20 ) primary key,
    id                       varchar(25 ) not null,
    profile_pic              varchar(1 ),
    quali_certificate1       varchar(1 ),
    quali_certificate2       varchar(1 ),
    quali_certificate3       varchar(1 ),
    quali_certificate4       varchar(1 ),
    quali_certificate5       varchar(1 ),
    quali_certificate6       varchar(1 ),
    ramp_certificate         varchar(1 ),
    prof_certificate         varchar(1 ),
	medi_council_registration varchar(1 ),
    health_dep_certificate   varchar(1 ),
    ayush_dep_registration   varchar(1 ),
    other_lic_certificate1   varchar(1 ),
    other_lic_certificate2   varchar(1 ),
    other_lic_certificate3   varchar(1 ),
    professional_workimg1    varchar(1 ),
    professional_workimg2    varchar(1 ),
    professional_workimg3    varchar(1 ),
    website_link             varchar(200 ),
    facebook_link            varchar(200 ),
    instagram_link           varchar(200 ),
    youtube_link             varchar(200 ),
    twitter_link             varchar(200 ),
    experience_comments      varchar(500 ),
    achievements_comments    varchar(250 ),
	signature_img			varchar(50 ),
	aadhar varchar(3 ),
	aadhar_back varchar(3 ),
	panback varchar(3 ),
	panfront varchar(3 ),
    foreign key ( id )
        references ampamt_account ( id )
);



create table business_documents_details (
    document_id             varchar(25 ) primary key,
    id                      varchar(25 ),
    company_logo            varchar(1 ),
    msme_certificate        varchar(1 ),
    company_pan             varchar(1 ),
    company_aadhar          varchar(1 ),
    company_aadhar_back 	varchar(1 ),
    gumasta_certificate     varchar(1 ),
    iso_certificate         varchar(1 ),
    gst_certificate         varchar(1 ),
    compliance_certificate  varchar(1 ),
    quali_certificate1      varchar(1 ),
    quali_certificate2      varchar(1 ),
    quali_certificate3      varchar(1 ),
    quali_certificate4      varchar(1 ),
    quali_certificate5      varchar(1 ),
    quali_certificate6      varchar(1 ),
    prof_certificate        varchar(1 ),
    other_lic_certificate1  varchar(1 ),
    other_lic_certificate2  varchar(1 ),
    other_lic_certificate3  varchar(1 ),
    professional_workimg1   varchar(1 ),
    professional_workimg2   varchar(1 ),
    professional_workimg3   varchar(1 ),
    website_link            varchar(200 ),
    facebook_link           varchar(200 ),
    instagram_link          varchar(200 ),
    youtube_link            varchar(200 ),
    twitter_link            varchar(200 ),
    about_comapny           varchar(500 ),
    detail_about_product    varchar(250 ),
	about_company 			 varchar(100 ),
    foreign key ( id )
        references ampamt_account ( id )
);
create table business_account_details (
    id                       varchar(25 ) primary key,
    name                     varchar(100 ),
    company_owner_name       varchar(100 ),
    owner_gender             varchar(1 ),
    representative_name      varchar(100 ),
    representative_gender    varchar(1 ),
    owner_nationality        varchar(30 ),
    owner_religion           varchar(30 ),
    owner_detail             varchar(100 ),
    sector                   varchar(4000 ),
   experienc_establishment varchar(8 ),
    regist_office_add        varchar(200 ),
    landmark                 varchar(100 ),
    city                     varchar(30 ),
    state                    varchar(30 ),
    zip_code                 varchar(30 ),
    contact_no               varchar(10 ),
    landline_no              varchar(10 ),
    other_contact_no         varchar(10 ),
    whatsapp_no              varchar(10 ),
    email_id                 varchar(50 ),
    other_email_id           varchar(50 ),
    aadhar_no                varchar(12 ),
    pan_no                   varchar(10 ),
    passport_no              varchar(20 ),
    tnc_accepted             varchar(1 ),
    active_flag              varchar(1 ),
    status                   varchar(20),
    account_type             varchar(10 ),
    create_date              date,
	owner_name_title 		varchar(10 ),
	 company_logo            varchar(50),
	 company_name              varchar(100 ),
	company_title 				varchar(30 ),
	representative_name_title  varchar(10 ),
	service_states varchar(150 ),
	service_cities varchar(4000 ),
	admin_flag varchar(1 ),
    foreign key ( id )
        references ampamt_account ( id )
);

create table ampamt_events (
    event_id           varchar(25 ) primary key,
    account_id         varchar(25 ),
    event_img          varchar(1 ),
    event_title        varchar(50 ),
    event_description  varchar(100 ),
    event_state        varchar(30 ),
    event_city         varchar(30 ),
    active_flag        varchar(1 ),
    foreign key ( account_id )
        references ampamt_account ( id )
);

create table ampamt_cash_account (
    cash_account_id   varchar(25 ) primary key,
    account_id        varchar(25 ),
    updated_by        varchar(25 ),
    available_amount  numeric(10, 2),
    create_date       date,
    update_date       date,
    foreign key ( account_id )
        references ampamt_account ( id )
);

create table ampamt_transactions (
    transaction_id    varchar(25 ) primary key,
    cash_account_id   varchar(25 ),
    account_id        varchar(25 ),
    paid_flag         varchar(1 ),
    amount            numeric(10, 2),
    create_date       date,
    transaction_date  date,
    status            varchar(10 ),
    remarks           varchar(100 ),
    payment_method    varchar(20 ),
	extra    		  varchar(4000 ),
	payment_id varchar(100 ),
	order_id varchar(100 ),
	 signature varchar(100 ),
	 update_date date,
    foreign key ( cash_account_id )
        references ampamt_cash_account ( cash_account_id )
);


create table ampamt_therapies (
    therapy_id    varchar(20 ) primary key,
    therapy_name  varchar(100 ),
    active_flag   varchar(1 )
);
create table advertisements (
    adv_id        varchar(25 ) primary key,
    account_id    varchar(25 ),
    create_date   date,
    display_flag  varchar(1 ),
    image_name    varchar(50 ),
	extras			 varchar(500 ),
    foreign key ( account_id )
        references ampamt_account ( id )
);

create table ampamt_audit_trail (
    id          varchar(30 ) primary key,
    account_id  varchar(30 ),
    action      varchar(1000 ),
    server_ip   varchar(50 ),
    client_ip   varchar(50 ),
    audit_date  date,
    module      varchar(50 )
);

create table tbl_state (
    state_code     varchar(10 ) primary key,
    state_name     varchar(100 ),
    state_country  varchar(100 )
);

create table tbl_city (
    city_code     varchar(10 ) primary key,
    state_code     varchar(100 ),
    city_name  varchar(100 ),
    foreign key ( state_code )
        references tbl_state ( state_code )
);
