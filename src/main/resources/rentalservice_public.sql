INSERT INTO public.role (role_id, name) VALUES (1, 'USER');
INSERT INTO public.role (role_id, name) VALUES (2, 'ADMIN');
INSERT INTO public.role (role_id, name) VALUES (3, 'MODERATOR');

INSERT INTO public.location (id, administrative_area, city, country, full_address, latitude, longitude, political, postal_code, route, street_number, sublocality) VALUES (1, 'Kyivs''ka oblast', 'Horenka', 'Ukraine', 'Kyivska Street, 218, Horenka, Kyivska oblast, Ukraine, 08105', 50.56762796661239, 30.318102892917324, 'Ukraine', '08105', 'Kyivska Street', '218', null);
INSERT INTO public.location (id, administrative_area, city, country, full_address, latitude, longitude, political, postal_code, route, street_number, sublocality) VALUES (2, 'L''vivs''ka oblast', 'Vynnyky', 'Ukraine', 'Khmelnytskoho St, 17, Vynnyky, Lvivska oblast, Ukraine, 79495', 49.817993353647594, 24.11082783756845, 'Ukraine', '79495', 'Khmel''nyts''koho Street', '17', null);
INSERT INTO public.location (id, administrative_area, city, country, full_address, latitude, longitude, political, postal_code, route, street_number, sublocality) VALUES (3, 'Ternopil''s''ka oblast', 'Ternopil', 'Ukraine', 'Tekstylna St, 22а, Ternopil, Ternopilska oblast, Ukraine, 46001', 49.571038102254526, 25.615954455417324, 'Ukraine', '46001', 'Tekstyl''na Street', '22а', null);
INSERT INTO public.location (id, administrative_area, city, country, full_address, latitude, longitude, political, postal_code, route, street_number, sublocality) VALUES (4, 'Ivano-Frankivs''ka oblast', 'Ivano-Frankivsk', 'Ukraine', 'Vidkryta St, 26, Ivano-Frankivsk, Ivano-Frankivska oblast, Ukraine, 76000', 48.919520416546256, 24.72155980881107, 'Ukraine', '76000', 'Vidkryta Street', '26', null);
INSERT INTO public.location (id, administrative_area, city, country, full_address, latitude, longitude, political, postal_code, route, street_number, sublocality) VALUES (5, 'L''vivs''ka oblast', 'Lviv', 'Ukraine', 'Lodiya St, 18А, Lviv, Lvivska oblast, Ukraine, 79000', 49.83659728373124, 24.093432873916555, 'Ukraine', '79000', 'Lodiya Street', '18А', 'Lychakivskyi District');
INSERT INTO public.location (id, administrative_area, city, country, full_address, latitude, longitude, political, postal_code, route, street_number, sublocality) VALUES (6, 'L''vivs''ka oblast', 'Zhovkva', 'Ukraine', 'Y.Slipoho Street, 33, Zhovkva, Lvivska oblast, Ukraine, 80300', 50.06046210097563, 23.978076428604055, 'Ukraine', '80300', 'Y.Slipoho Street', '33', null);
alter sequence location_id_seq restart with 7;


INSERT INTO public.account (id, activation_code, avatar_path, documents_verified, edited_at, email, first_name, is_disabled, is_locked, is_online, last_login_time, last_name, lock_reason, makler_probability_score, owning_apartments_count, password, phone_number, refresh_token_key, registered_at, username, account_role_id) VALUES (1, '701ead05-f3cc-48d7-8d23-5439e6df9818', null, false, '2020-05-27 10:24:13.025520', 'testuser1@gmail.com', 'test', false, false, false, '2020-05-27 10:23:10.717097', 'user', null, 0, 1, '$2a$10$v/Kx5pu19XaypF/CGLr/wOOdApd2Z/v7SPvlGaZosWngM8U/iXYyG', '+380634565234', '0d8740ab-abe1-48ea-980b-a90ac618f8cb', '2020-05-25 19:55:23.005310', 'testuser1', 1);
INSERT INTO public.account (id, activation_code, avatar_path, documents_verified, edited_at, email, first_name, is_disabled, is_locked, is_online, last_login_time, last_name, lock_reason, makler_probability_score, owning_apartments_count, password, phone_number, refresh_token_key, registered_at, username, account_role_id) VALUES (2, '13f95b84-09eb-4300-8395-fdd9055085ec', null, false, '2020-05-27 10:28:40.113798', 'maxtolen@gmail.com', 'Max', false, false, false, '2020-05-27 10:27:16.887301', 'Tolen', null, 0.6065306597126334, 3, '$2a$10$yz5PvtzSXuzWNG4NMsy9weKGlNSF1p0RzDGp22ENHPhPZxdbHKfT6', '+380674565434', '9228093f-132a-42e1-baa3-93ff365a619a', '2020-05-25 20:42:34.132960', 'maxtolen', 1);
alter sequence account_id_seq restart with 3;

INSERT INTO public.apartment (apartment_id, allow_pets, building_type, create_date, description, edit_date, floor, has_photos, living_area, number_of_rooms, price, status, status_date_change, tags, title, total_area, wall_type, location_id, account_id) VALUES (1, true, 1, '2020-05-25 20:23:06.720167', 'The Veranda offers beautiful, pet-friendly surroundings that include a pool, basketball court, a state-of-the-art theater room with surround sound, and a fully-equipped fitness center. The Veranda features five distinctive floor plans with one, two, or three bedrooms, complete with designer kitchens, 9-foot ceilings, detailed crown molding, paneled doors, and wood grain entries. Relax in your new home which has been thoughtfully equipped with a full-size washer & dryer, built-in microwave, frost-free refrigerator with ice maker, bedroom and living room ceiling fans, and every detail to make your daily living a breeze. Enjoy a feeling of serenity and comfort in your apartment home at The Veranda.', '2020-05-25 20:23:06.720167', 3, false, 120, 4, 800, 'FREE', '2020-05-25 20:23:06.696197', '#kyiv#center', 'The Veranda flat', 150, null, 1, 1);
INSERT INTO public.apartment (apartment_id, allow_pets, building_type, create_date, description, edit_date, floor, has_photos, living_area, number_of_rooms, price, status, status_date_change, tags, title, total_area, wall_type, location_id, account_id) VALUES (2, true, 1, '2020-05-25 21:44:54.787289', 'Cutting edge amenities, meticulously-groomed grounds, and a dedicated staff contributes to a higher standard of living. Convenient shopping, award-winning schools, local museums, and parks are all close at hand', '2020-05-25 21:44:54.787289', 6, false, 50, 2, 400, 'FREE', '2020-05-25 21:44:54.767316', '#lviv#allowPets#new', 'Del Sol Apartments', 75, null, 2, 2);
INSERT INTO public.apartment (apartment_id, allow_pets, building_type, create_date, description, edit_date, floor, has_photos, living_area, number_of_rooms, price, status, status_date_change, tags, title, total_area, wall_type, location_id, account_id) VALUES (3, false, 1, '2020-05-25 21:48:58.414418', 'easy access to shopping and employment, and very close to recreational activities of the Galveston Bay and the Gulf Coast. The property is well maintained and responsive to tenant needs. The apartments have been thoroughly remodeled with new porcelain tile floors throughout, elegant color scheme, new bathrooms, etc. Come visit your new Home where you can LIVE, WORK and PLAY in quality.', '2020-05-25 21:48:58.414418', 16, false, 70, 3, 600, 'FREE', '2020-05-25 21:48:58.406427', '#ternopil#center', 'The Cambria', 95, null, 3, 2);
INSERT INTO public.apartment (apartment_id, allow_pets, building_type, create_date, description, edit_date, floor, has_photos, living_area, number_of_rooms, price, status, status_date_change, tags, title, total_area, wall_type, location_id, account_id) VALUES (4, true, 1, '2020-05-25 22:28:17.188473', 'Just minutes from restaurants, parks, and shopping, Baypointe Manor has a great location that is guaranteed to make your life comfortable and convenient. With 4 different floor plans to choose from, finding the right apartment home to fit your needs has never been easier.', '2020-05-25 22:28:17.188473', 1, false, 150, 5, 1200, 'FREE', '2020-05-25 22:28:17.183484', '#center#big', 'Baypointe Manor', 190, null, 4, 1);
INSERT INTO public.apartment (apartment_id, allow_pets, building_type, create_date, description, edit_date, floor, has_photos, living_area, number_of_rooms, price, status, status_date_change, tags, title, total_area, wall_type, location_id, account_id) VALUES (5, true, 1, '2020-05-25 22:31:21.470752', 'Our spacious and affordable apartment homes include an "All Inclusive" package. You only need to make one monthly payment, and your bills won''t fluctuate in HOT summer months. No additional deposits or fees to set-up all your utility accounts or transfer accounts. Allow cat and dog', '2020-05-25 22:31:21.470752', 20, false, 40, 1, 200, 'FREE', '2020-05-25 22:31:21.466722', '#lviv#smallFlat', 'Coral Manor Apartments', 60, null, 5, 2);
INSERT INTO public.apartment (apartment_id, allow_pets, building_type, create_date, description, edit_date, floor, has_photos, living_area, number_of_rooms, price, status, status_date_change, tags, title, total_area, wall_type, location_id, account_id) VALUES (6, true, 0, '2020-05-25 23:08:44.794976', 'You''ll find beautiful, pet-friendly, 1 and 2 bedroom apartments. Located in a gorgeous neighborhood with quick access to Galveston Bay, Clear Lake and many major highways allowing you to relax and enjoy the water or a day of shopping at the mall. Contact us today or visit our website at www.banyanbaytx.com to schedule a tour of our apartments and see all the reasons why you will want to make Banyan Bay Apartments your new home!', '2020-05-25 23:08:44.794976', 3, false, 40, 2, 300, 'FREE', '2020-05-25 23:08:44.769028', '#allowCats', 'Banyan Bay Apartment Homes', 60, null, 6, 2);
alter sequence apartment_apartment_id_seq restart with 8;

INSERT INTO public.apartment_image_links (apartment_apartment_id, image_links) VALUES (1, 'https://rentalservicephotobucket.s3-eu-west-1.amazonaws.com/photos/1/apartment/khkutbqxybtltrmvtmja.jpg');
INSERT INTO public.apartment_image_links (apartment_apartment_id, image_links) VALUES (2, 'https://rentalservicephotobucket.s3-eu-west-1.amazonaws.com/photos/2/apartment/skfdwzrmpsqhpksimfdu.jpg');
INSERT INTO public.apartment_image_links (apartment_apartment_id, image_links) VALUES (3, 'https://rentalservicephotobucket.s3-eu-west-1.amazonaws.com/photos/2/apartment/pscbpousdabktiucfffm.jpg');
INSERT INTO public.apartment_image_links (apartment_apartment_id, image_links) VALUES (3, 'https://rentalservicephotobucket.s3-eu-west-1.amazonaws.com/photos/2/apartment/bjllgpyzpembbhplmmvh.jpg');
INSERT INTO public.apartment_image_links (apartment_apartment_id, image_links) VALUES (3, 'https://rentalservicephotobucket.s3-eu-west-1.amazonaws.com/photos/2/apartment/uvvyubxbypeiwyltwbnb.jpg');
INSERT INTO public.apartment_image_links (apartment_apartment_id, image_links) VALUES (4, 'https://rentalservicephotobucket.s3-eu-west-1.amazonaws.com/photos/1/apartment/uoaayqyteugiobgeukbl.jpg');
INSERT INTO public.apartment_image_links (apartment_apartment_id, image_links) VALUES (5, 'https://rentalservicephotobucket.s3-eu-west-1.amazonaws.com/photos/2/apartment/ipnosxcygifnbffvxqiw.jpg');
INSERT INTO public.apartment_image_links (apartment_apartment_id, image_links) VALUES (6, 'https://rentalservicephotobucket.s3-eu-west-1.amazonaws.com/photos/2/apartment/msbzddrdvdysqlupvqlh.jpg');
INSERT INTO public.apartment_image_links (apartment_apartment_id, image_links) VALUES (6, 'https://rentalservicephotobucket.s3-eu-west-1.amazonaws.com/photos/2/apartment/ydmlgwgjemeweqbdvysp.jpg');
alter sequence apartment_apartment_id_seq restart with 11;

INSERT INTO public.comment (id, content, created_at, modified_at, apartment_id, account_id) VALUES (11, 'I think this apartment is really bad. Please, destroy it!', '2020-05-27 10:16:12.848225', null, 6, 1);
INSERT INTO public.comment (id, content, created_at, modified_at, apartment_id, account_id) VALUES (13, 'Why do you think so? Please send me your feedback and I will update apartment accommodations. ', '2020-05-27 10:27:16.907218', null, 6, 2);
alter sequence comment_id_seq restart with 3;
