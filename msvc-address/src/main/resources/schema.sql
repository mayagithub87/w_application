;
CREATE SEQUENCE "PUBLIC"."WM_ADDRESSES_SEQ" START WITH 1 RESTART WITH 101 INCREMENT BY 50;
CREATE
MEMORY TABLE "PUBLIC"."WM_ADDRESSES"(
    "ADDRESS_ID" BIGINT NOT NULL,
    "ADDRESS" CHARACTER VARYING(255),
    "CREATED_DATE" TIMESTAMP(6),
    "IS_ENABLED" BOOLEAN,
    "LAST_MODIFIED_DATE" TIMESTAMP(6),
    "VERSION" BIGINT
);
ALTER TABLE "PUBLIC"."WM_ADDRESSES"
    ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E" PRIMARY KEY ("ADDRESS_ID");
ALTER TABLE "PUBLIC"."WM_ADDRESSES"
    ADD CONSTRAINT "PUBLIC"."UK_HX3K4IRI0AB40FMIFWCXG3A7A" UNIQUE ("ADDRESS");
