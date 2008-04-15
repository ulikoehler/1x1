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
 	
namespace Encrypt.NET
{
	partial class EncryptWindow
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		
		/// <summary>
		/// Disposes resources used by the form.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing) {
				if (components != null) {
					components.Dispose();
				}
			}
			base.Dispose(disposing);
		}
		
		/// <summary>
		/// This method is required for Windows Forms designer support.
		/// Do not change the method contents inside the source code editor. The Forms designer might
		/// not be able to load this method if it was changed manually.
		/// </summary>
		private void InitializeComponent()
		{
			this.inputTextBox = new System.Windows.Forms.RichTextBox();
			this.passwordTextBox = new System.Windows.Forms.TextBox();
			this.okButton = new System.Windows.Forms.Button();
			this.outputTextBox = new System.Windows.Forms.RichTextBox();
			this.decryptCheckbox = new System.Windows.Forms.CheckBox();
			this.algorithmBox = new System.Windows.Forms.GroupBox();
			this.aesRadioButton = new System.Windows.Forms.RadioButton();
			this.tripleDESRadioButton = new System.Windows.Forms.RadioButton();
			this.desRadioButton = new System.Windows.Forms.RadioButton();
			this.algorithmBox.SuspendLayout();
			this.SuspendLayout();
			// 
			// inputTextBox
			// 
			this.inputTextBox.Location = new System.Drawing.Point(12, 12);
			this.inputTextBox.Name = "inputTextBox";
			this.inputTextBox.Size = new System.Drawing.Size(251, 122);
			this.inputTextBox.TabIndex = 0;
			this.inputTextBox.Text = "";
			// 
			// passwordTextBox
			// 
			this.passwordTextBox.Location = new System.Drawing.Point(13, 158);
			this.passwordTextBox.Name = "passwordTextBox";
			this.passwordTextBox.Size = new System.Drawing.Size(250, 20);
			this.passwordTextBox.TabIndex = 1;
			this.passwordTextBox.UseSystemPasswordChar = true;
			// 
			// okButton
			// 
			this.okButton.Location = new System.Drawing.Point(13, 184);
			this.okButton.Name = "okButton";
			this.okButton.Size = new System.Drawing.Size(145, 23);
			this.okButton.TabIndex = 2;
			this.okButton.Text = "OK";
			this.okButton.UseVisualStyleBackColor = true;
			this.okButton.Click += new System.EventHandler(this.processOkClick);
			// 
			// outputTextBox
			// 
			this.outputTextBox.Location = new System.Drawing.Point(12, 268);
			this.outputTextBox.Name = "outputTextBox";
			this.outputTextBox.ReadOnly = true;
			this.outputTextBox.Size = new System.Drawing.Size(250, 146);
			this.outputTextBox.TabIndex = 3;
			this.outputTextBox.Text = "";
			// 
			// decryptCheckbox
			// 
			this.decryptCheckbox.Location = new System.Drawing.Point(161, 184);
			this.decryptCheckbox.Name = "decryptCheckbox";
			this.decryptCheckbox.Size = new System.Drawing.Size(104, 24);
			this.decryptCheckbox.TabIndex = 4;
			this.decryptCheckbox.Text = "Entschlüsseln";
			this.decryptCheckbox.UseVisualStyleBackColor = true;
			// 
			// algorithmBox
			// 
			this.algorithmBox.Controls.Add(this.desRadioButton);
			this.algorithmBox.Controls.Add(this.tripleDESRadioButton);
			this.algorithmBox.Controls.Add(this.aesRadioButton);
			this.algorithmBox.Location = new System.Drawing.Point(13, 213);
			this.algorithmBox.Name = "algorithmBox";
			this.algorithmBox.Size = new System.Drawing.Size(249, 49);
			this.algorithmBox.TabIndex = 5;
			this.algorithmBox.TabStop = false;
			this.algorithmBox.Text = "Algorithmus";
			// 
			// aesRadioButton
			// 
			this.aesRadioButton.Checked = true;
			this.aesRadioButton.Location = new System.Drawing.Point(6, 19);
			this.aesRadioButton.Name = "aesRadioButton";
			this.aesRadioButton.Size = new System.Drawing.Size(104, 24);
			this.aesRadioButton.TabIndex = 0;
			this.aesRadioButton.TabStop = true;
			this.aesRadioButton.Text = "&AES";
			this.aesRadioButton.UseVisualStyleBackColor = true;
			// 
			// tripleDESRadioButton
			// 
			this.tripleDESRadioButton.Location = new System.Drawing.Point(82, 19);
			this.tripleDESRadioButton.Name = "tripleDESRadioButton";
			this.tripleDESRadioButton.Size = new System.Drawing.Size(104, 24);
			this.tripleDESRadioButton.TabIndex = 1;
			this.tripleDESRadioButton.TabStop = true;
			this.tripleDESRadioButton.Text = "&3DES";
			this.tripleDESRadioButton.UseVisualStyleBackColor = true;
			// 
			// desRadioButton
			// 
			this.desRadioButton.Location = new System.Drawing.Point(148, 19);
			this.desRadioButton.Name = "desRadioButton";
			this.desRadioButton.Size = new System.Drawing.Size(104, 24);
			this.desRadioButton.TabIndex = 2;
			this.desRadioButton.TabStop = true;
			this.desRadioButton.Text = "&DES";
			this.desRadioButton.UseVisualStyleBackColor = true;
			// 
			// EncryptWindow
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(277, 426);
			this.Controls.Add(this.algorithmBox);
			this.Controls.Add(this.decryptCheckbox);
			this.Controls.Add(this.outputTextBox);
			this.Controls.Add(this.okButton);
			this.Controls.Add(this.passwordTextBox);
			this.Controls.Add(this.inputTextBox);
			this.Name = "EncryptWindow";
			this.Text = "EncryptWindow";
			this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.close);
			this.algorithmBox.ResumeLayout(false);
			this.ResumeLayout(false);
			this.PerformLayout();
		}
		private System.Windows.Forms.RadioButton desRadioButton;
		private System.Windows.Forms.RadioButton tripleDESRadioButton;
		private System.Windows.Forms.RadioButton aesRadioButton;
		private System.Windows.Forms.GroupBox algorithmBox;
		private System.Windows.Forms.CheckBox decryptCheckbox;
		private System.Windows.Forms.RichTextBox outputTextBox;
		private System.Windows.Forms.RichTextBox inputTextBox;
		private System.Windows.Forms.TextBox passwordTextBox;
		private System.Windows.Forms.Button okButton;
		
		void processOkClick(object sender, System.EventArgs e)
		{
			if(aesRadioButton.Checked)
				{	
				if(decryptCheckbox.Checked){outputTextBox.Text = AESDecryptMessage(inputTextBox.Text, passwordTextBox.Text);}
				else{outputTextBox.Text = AESEncryptMessage(inputTextBox.Text, passwordTextBox.Text);}
				}
			else if(tripleDESRadioButton.Checked)
				{
				if(decryptCheckbox.Checked){outputTextBox.Text = TDESDecryptMessage(inputTextBox.Text, passwordTextBox.Text);}
				else{outputTextBox.Text = TDESEncryptMessage(inputTextBox.Text, passwordTextBox.Text);}
				}
			else if(desRadioButton.Checked)
				{
				if(decryptCheckbox.Checked){outputTextBox.Text = DESDecryptMessage(inputTextBox.Text, passwordTextBox.Text);}
				else{outputTextBox.Text = DESEncryptMessage(inputTextBox.Text, passwordTextBox.Text);}
				}
		}
		
		void close(object sender, FormClosedEventArgs e)
		{
			Application.Exit();
		}
   }
}
