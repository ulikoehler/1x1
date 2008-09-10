BlockCipher engine = new TwofishEngine();
BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));
//Get data and encrypt
byte[] passwordBytes = new String(passwordField.getPassword()).getBytes();
byte[] input;
//Base64-decode the ciphertext
if(decryptCheckbox.isSelected()) {input = Base64.decode(inputField.getText().getBytes());}
else {input = inputField.getText().getBytes();}

//Hash the password to fit it into the right size
Digest digest = new SHA256Digest();
digest.update(passwordBytes, 0, passwordBytes.length);
byte[] keyBytes = new byte[32];
digest.doFinal(keyBytes, 0);

cipher.init(!decryptCheckbox.isSelected(), new KeyParameter(keyBytes));

byte[] output = new byte[cipher.getOutputSize(input.length)];
int outputLen = cipher.processBytes(input, 0, input.length, output, 0);
cipher.doFinal(output, outputLen);
//Print the output into outputField and Base64-encode if we have to encrypt
if(decryptCheckbox.isSelected()) {outputField.setText(new String(output));}
else {outputField.setText(new String(Base64.encode(output)));}