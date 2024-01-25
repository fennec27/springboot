
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'springboot_schema' AND sequencename = 'book_seq') THEN
CREATE SEQUENCE springboot_schema.book_seq START 1;
END IF;
END $$;