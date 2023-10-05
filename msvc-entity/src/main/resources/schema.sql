;
CREATE
USER IF NOT EXISTS "SA" SALT 'ba139f1269a5eab0' HASH '10337bae5ccf455ed713e1e5a6212bca9fc98878b44c5c7cf37cb80cbbd5fd2c' ADMIN;
CREATE SEQUENCE "PUBLIC"."WM_ENTITIES_SEQ" START WITH 1 RESTART WITH 101 INCREMENT BY 50;
CREATE
MEMORY TABLE "PUBLIC"."WM_ENTITIES"(
    "ENTITY_ID" BIGINT NOT NULL,
    "ADDRESS_ID" BIGINT,
    "CREATED_DATE" TIMESTAMP(6),
    "IS_ENABLED" BOOLEAN,
    "LAST_MODIFIED_DATE" TIMESTAMP(6),
    "NAME" CHARACTER VARYING(255),
    "NIF" CHARACTER VARYING(255),
    "VERSION" BIGINT
);
ALTER TABLE "PUBLIC"."WM_ENTITIES"
    ADD CONSTRAINT "PUBLIC"."CONSTRAINT_C" PRIMARY KEY ("ENTITY_ID");
ALTER TABLE "PUBLIC"."WM_ENTITIES"
    ADD CONSTRAINT "PUBLIC"."UK_G4A98734MSUBHP6C3KTH53SAS" UNIQUE ("NIF");
ALTER TABLE "PUBLIC"."WM_ENTITIES"
    ADD CONSTRAINT "PUBLIC"."UK_G5YSAF0QCBCXXQ3PU6NNNVY4V" UNIQUE ("ADDRESS_ID");
ALTER TABLE "PUBLIC"."WM_ENTITIES"
    ADD CONSTRAINT "PUBLIC"."UK_2RPGEJTPJQ3W95QFOQ7DWXA8S" UNIQUE ("NAME");
