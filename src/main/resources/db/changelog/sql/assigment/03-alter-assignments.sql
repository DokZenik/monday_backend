ALTER TABLE assignments
    ALTER COLUMN teacher_id SET NOT NULL,
    ALTER COLUMN status SET NOT NULL,
    ALTER COLUMN description SET NOT NULL;