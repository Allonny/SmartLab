void setup(void) {
  pinMode(13, OUTPUT);
  Serial.begin(SERIAL_SPEED);
  
  nfc.begin();
  if ( !(nfc.getFirmwareVersion()) ) error_response(KEYWORD_SCANNER_NOT_FOUND);
  nfc.SAMConfig();
  settings_load();

  for_send(STARTUP_LABEL, KEYWORD_DONE);
}

void loop(void) {
  if ( (millis() - RFID_polling_timer >= RFID_polling_time + RFID_timout_time) && !RFID_error ) {
    RFID_polling_timer = millis();
    RFID_read();
  }
  
  if ( millis() - serial_polling_timer >= serial_polling_time ) {
    serial_polling_timer = millis();
    get_data();
    send_data();
  }
}
