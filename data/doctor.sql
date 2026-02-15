CREATE DATABASE IF NOT EXISTS medical;

USE medical;

INSERT INTO doctor (
    doctor_id,
    doctor_name,
    doctor_address,
    cabinet_name,
    doctor_phone,
    doctor_email,
    doctor_specialty,
    doctor_description,
    doctor_status,
    doctor_creation_at
) VALUES
      (
          'a3f6b1c4-9f3d-4c1e-8c27-6d8e4f2a9b01',
          'Dr. Antoine Moreau',
          '18 Rue de Rivoli, 75004 Paris',
          'Cabinet Médical Rivoli',
          '0142285734',
          'antoine.moreau@medicentre.fr',
          'CARDIOLOGY',
          'Cardiologue spécialisé en prévention cardiovasculaire et suivi post-opératoire.',
          true,
          '2024-01-15 09:30:00'
      ),
      (
          'b7e1c2d9-5a44-4a6a-b7c1-3e92d8f6c210',
          'Dr. Camille Lefèvre',
          '72 Boulevard Haussmann, 75008 Paris',
          'Clinique Haussmann',
          '0156347821',
          'camille.lefevre@cliniqueh.fr',
          'DERMATOLOGY',
          'Dermatologue experte en pathologies cutanées chroniques et esthétique médicale.',
          true,
          '2024-02-03 14:15:00'
      ),
      (
          'c9d4f1a2-6b88-4d1e-9e3a-7f45a2d6b932',
          'Dr. Nicolas Girard',
          '5 Place Bellecour, 69002 Lyon',
          'Centre Médical Bellecour',
          '0472569812',
          'nicolas.girard@cmbellecour.fr',
          'GENERAL_PRACTICE',
          'Médecin généraliste assurant suivi familial, vaccinations et bilans de santé.',
          true,
          '2024-03-10 08:45:00'
      ),
      (
          'd2f8a7b3-1c55-4f9a-a3b4-8d19e6c4f521',
          'Dr. Élodie Martin',
          '34 Avenue Jean Médecin, 06000 Nice',
          'Cabinet Jean Médecin',
          '0493124578',
          'elodie.martin@cabinetjm.fr',
          'PEDIATRICS',
          'Pédiatre spécialisée en suivi du nourrisson et maladies infantiles.',
          true,
          '2024-04-18 11:00:00'
      ),
      (
          'e5a1c8d7-2f66-4b7e-b2c8-9f31d5e7a843',
          'Dr. Thomas Bernard',
          '12 Rue Sainte-Catherine, 33000 Bordeaux',
          'Clinique Sainte-Catherine',
          '0556782345',
          'thomas.bernard@clinique-sc.fr',
          'ORTHOPEDICS',
          'Chirurgien orthopédique spécialisé en traumatologie et médecine du sport.',
          false,
          '2024-05-22 16:20:00'
      );
