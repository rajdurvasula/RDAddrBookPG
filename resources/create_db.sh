#!/bin/bash
psql "sslmode=verify-ca sslrootcert=/var/lib/pgsql/az_root.crt host=rdpgdb1.postgres.database.azure.com dbname=postgres user=postgres@rdpgdb1"
