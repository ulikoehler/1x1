/*
 * Erstellt mit SharpDevelop.
 * Benutzer: User
 * Datum: 04.11.2007
 * Zeit: 15:31
 * 
 * Sie können diese Vorlage unter Extras > Optionen > Codeerstellung > Standardheader ändern.
 */

using System;
using System.Drawing;
using System.Windows.Forms;
using System.IO;
using System.Text;
using System.Security.Cryptography;

namespace Encrypt.NET
{
	/// <summary>
	/// Description of EncryptWindow.
	/// </summary>
	public partial class EncryptWindow : Form
	{
		public EncryptWindow()
		{
			//
			// The InitializeComponent() call is required for Windows Forms designer support.
			//
			InitializeComponent();
			
			//
			// TODO: Add constructor code after the InitializeComponent() call.
			//
		}
		
		public static string TDESEncryptMessage(string plainMessage, string password)
        {
            TripleDESCryptoServiceProvider des = new TripleDESCryptoServiceProvider();
            des.IV = new byte[8];
            PasswordDeriveBytes pdb = new PasswordDeriveBytes(password, new byte[0]);
            des.Key = pdb.CryptDeriveKey("RC2", "MD5", 128, new byte[8]);
            MemoryStream ms = new MemoryStream(plainMessage.Length * 2);
            CryptoStream encStream = new CryptoStream(ms, des.CreateEncryptor(), CryptoStreamMode.Write);
            byte[] plainBytes = Encoding.UTF8.GetBytes(plainMessage);
            encStream.Write(plainBytes, 0, plainBytes.Length);
            encStream.FlushFinalBlock();
            byte[] encryptedBytes = new byte[ms.Length];
            ms.Position = 0;
            ms.Read(encryptedBytes, 0, (int)ms.Length);
            encStream.Close();
            return Convert.ToBase64String(encryptedBytes);
        }
        
        public static string TDESDecryptMessage(string encryptedBase64, string password)
        {
        	try
	        	{
	            TripleDESCryptoServiceProvider des = new TripleDESCryptoServiceProvider();
	            des.IV = new byte[8];
	            PasswordDeriveBytes pdb = new PasswordDeriveBytes(password, new byte[0]);
	            des.Key = pdb.CryptDeriveKey("RC2", "MD5", 128, new byte[8]);
	            byte[] encryptedBytes = Convert.FromBase64String(encryptedBase64);
	            MemoryStream ms = new MemoryStream(encryptedBase64.Length);
	            CryptoStream decStream = new CryptoStream(ms, des.CreateDecryptor(), CryptoStreamMode.Write);
	            decStream.Write(encryptedBytes, 0, encryptedBytes.Length);
	            decStream.FlushFinalBlock();
	            byte[] plainBytes = new byte[ms.Length];
	            ms.Position = 0;
	            ms.Read(plainBytes, 0, (int)ms.Length);
	            decStream.Close();
	            return Encoding.UTF8.GetString(plainBytes);
	        	}
        	catch(CryptographicException)
        		{
        		MessageBox.Show("Falsches Passwort!", "Passwortfehler", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
        		return "";
        		}
        }
        
        public static string DESEncryptMessage(string plainMessage, string password)
        {
            DESCryptoServiceProvider des = new DESCryptoServiceProvider();
            des.IV = new byte[8];
            PasswordDeriveBytes pdb = new PasswordDeriveBytes(password, new byte[0]);
            des.Key = pdb.CryptDeriveKey("RC2", "MD5", 128, new byte[8]);
            MemoryStream ms = new MemoryStream(plainMessage.Length * 2);
            CryptoStream encStream = new CryptoStream(ms, des.CreateEncryptor(), CryptoStreamMode.Write);
            byte[] plainBytes = Encoding.UTF8.GetBytes(plainMessage);
            encStream.Write(plainBytes, 0, plainBytes.Length);
            encStream.FlushFinalBlock();
            byte[] encryptedBytes = new byte[ms.Length];
            ms.Position = 0;
            ms.Read(encryptedBytes, 0, (int)ms.Length);
            encStream.Close();
            return Convert.ToBase64String(encryptedBytes);
        }
        
        public static string DESDecryptMessage(string encryptedBase64, string password)
        {
        	try
	        	{
	            DESCryptoServiceProvider des = new DESCryptoServiceProvider();
	            des.IV = new byte[8];
	            PasswordDeriveBytes pdb = new PasswordDeriveBytes(password, new byte[0]);
	            des.Key = pdb.CryptDeriveKey("RC2", "MD5", 128, new byte[8]);
	            byte[] encryptedBytes = Convert.FromBase64String(encryptedBase64);
	            MemoryStream ms = new MemoryStream(encryptedBase64.Length);
	            CryptoStream decStream = new CryptoStream(ms, des.CreateDecryptor(), CryptoStreamMode.Write);
	            decStream.Write(encryptedBytes, 0, encryptedBytes.Length);
	            decStream.FlushFinalBlock();
	            byte[] plainBytes = new byte[ms.Length];
	            ms.Position = 0;
	            ms.Read(plainBytes, 0, (int)ms.Length);
	            decStream.Close();
	            return Encoding.UTF8.GetString(plainBytes);
	        	}
        	catch(CryptographicException)
        		{
        		MessageBox.Show("Falsches Passwort!", "Passwortfehler", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
        		return "";
        		}
        }
        
		public static string AESEncryptMessage(string text, string password)
		{
			byte[] iv = new byte[16];
            PasswordDeriveBytes pdb = new PasswordDeriveBytes(password, new byte[0]);
            byte[] key = pdb.GetBytes(32);
  			byte[] textBytes = Encoding.UTF8.GetBytes(text);
            RijndaelManaged symmKey = new RijndaelManaged ();
			symmKey.Mode = CipherMode.CBC;
			ICryptoTransform enc = symmKey.CreateEncryptor(key, iv);
			MemoryStream mem = new MemoryStream ();
			CryptoStream cry = new CryptoStream (mem, enc, CryptoStreamMode.Write);
			cry.Write(textBytes, 0, textBytes.Length);
			cry.FlushFinalBlock();
			byte[] cipBytes = mem.ToArray();
			mem.Close();
			cry.Close();
			return Convert.ToBase64String(cipBytes);
		}
		
		public static string AESDecryptMessage(string ciphertext, string password)
		{
			byte[] ivBytes = new byte[16];
			byte[]cipTextBytes = Convert.FromBase64String(ciphertext);
			PasswordDeriveBytes pwd = new PasswordDeriveBytes (password, new byte[0]);
			byte[] keyBytes = pwd.GetBytes(32);
			RijndaelManaged symmKey = new RijndaelManaged ();
			symmKey.Mode = CipherMode.CBC;
			ICryptoTransform dec = symmKey.CreateDecryptor(keyBytes, ivBytes);
			MemoryStream mem = new MemoryStream (cipTextBytes);
			CryptoStream cry  = new CryptoStream (mem, dec, CryptoStreamMode.Read);
			byte[] textBytes = new byte[cipTextBytes.Length];
			int decByteCount = cry.Read(textBytes, 0, textBytes.Length);
			mem.Close();
			cry.Close();
			return Encoding.UTF8.GetString(textBytes, 0, decByteCount);
		}
	}
}
