SELECT
    LOGGER,
    LEVEL,
    MESSAGE->DESERIALIZATIONERROR->ERRORMESSAGE
FROM KSQL_PROCESSING_LOG LIMIT 1;