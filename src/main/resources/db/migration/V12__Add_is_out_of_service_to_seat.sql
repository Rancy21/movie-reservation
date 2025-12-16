-- Add is_out_of_service column to seat table
ALTER TABLE seat ADD COLUMN is_out_of_service BOOLEAN DEFAULT FALSE NOT NULL;
