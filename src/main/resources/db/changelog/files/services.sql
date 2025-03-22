-- SERVICES
INSERT INTO services(ID, UUID, NAME, DESCRIPTION, PLAN, ACTIVE, CREATED_BY, CREATION_DATE, UPDATE_DATE, UPDATED_BY)
VALUES (1, uuid(), 'Image Resizing', 'Resizing Images', 'BASIC', 1, '85f7a4ef-8498-47c6-8832-8c5050f879f2', '2025-03-16 09:00:00.000', NULL, NULL),
       (2, uuid(),  'Scale Master','Change Images Scales', 'PREMIUM', 1, '85f7a4ef-8498-47c6-8832-8c5050f879f2', '2025-03-16 09:00:00.000', NULL, NULL),
       (3, uuid(),  'Image Enhancer','Enhance Images', 'PREMIUM', 1, '85f7a4ef-8498-47c6-8832-8c5050f879f2', '2025-03-16 09:00:00.000', NULL, NULL),
       (4, uuid(),  'Photo Optimizer','Optimize Images', 'PREMIUM', 1, '85f7a4ef-8498-47c6-8832-8c5050f879f2', '2025-03-16 09:00:00.000', NULL, NULL),
       (5, uuid(),  'Image Compreeser','Compress Images', 'PREMIUM', 1, '85f7a4ef-8498-47c6-8832-8c5050f879f2', '2025-03-16 09:00:00.000', NULL, NULL);