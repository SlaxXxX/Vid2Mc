#pragma once

#include <stdlib.h>
#include "MediaManager.h"
#include "Process.h"

namespace Vid2Mc {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for Options
	/// </summary>
	public ref class Options : public System::Windows::Forms::Form
	{
	public:
		Options(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~Options()
		{
			if (components)
			{
				delete components;
			}
		}

	private:
		MediaManager* mediaManager = new MediaManager;
		Process* process = new Process;

		void update() {
			pixel_w->Text = "" + mediaManager->getProp(pixel_w->Name);
			pixel_h->Text = "" + mediaManager->getProp(pixel_h->Name);
			block_w->Text = "" + mediaManager->getProp(block_w->Name);
			block_h->Text = "" + mediaManager->getProp(block_h->Name);
			pixel_size->Text = "" + mediaManager->getProp(pixel_size->Name);
			fps->Text = "" + mediaManager->getProp(fps->Name);
		}

	private: System::Windows::Forms::OpenFileDialog^  openFileDialog1;
	private: System::Windows::Forms::Button^  coose_file;



	private: System::Windows::Forms::Label^  label1;
	private: System::Windows::Forms::TextBox^  pixel_w;

	private: System::Windows::Forms::Label^  label2;
	private: System::Windows::Forms::TextBox^  pixel_h;

	private: System::Windows::Forms::Button^  goButton;
	private: System::Windows::Forms::TextBox^  file_output;


	private: System::Windows::Forms::ProgressBar^  progressBar1;

	private: bool fileChosen = false;
	private: System::Windows::Forms::Panel^  panel1;
	private: System::Windows::Forms::Label^  label3;
	private: System::Windows::Forms::Label^  label6;
	private: System::Windows::Forms::TextBox^  pixel_size;



	private: System::Windows::Forms::Label^  label4;
	private: System::Windows::Forms::TextBox^  block_h;

	private: System::Windows::Forms::TextBox^  block_w;

	private: System::Windows::Forms::Label^  label5;
	private: System::Windows::Forms::ColorDialog^  colorDialog1;
	private: System::Windows::Forms::PictureBox^  bg_color;

	private: System::Windows::Forms::Label^  label7;
	private: System::Windows::Forms::TextBox^  fps;

	private: System::Windows::Forms::Label^  label8;
	private: System::Windows::Forms::Label^  label9;
	private: System::Windows::Forms::TextBox^  video_name;


	private: System::ComponentModel::IContainer^  components;
	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>


#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->openFileDialog1 = (gcnew System::Windows::Forms::OpenFileDialog());
			this->coose_file = (gcnew System::Windows::Forms::Button());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->pixel_w = (gcnew System::Windows::Forms::TextBox());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->pixel_h = (gcnew System::Windows::Forms::TextBox());
			this->goButton = (gcnew System::Windows::Forms::Button());
			this->file_output = (gcnew System::Windows::Forms::TextBox());
			this->progressBar1 = (gcnew System::Windows::Forms::ProgressBar());
			this->panel1 = (gcnew System::Windows::Forms::Panel());
			this->fps = (gcnew System::Windows::Forms::TextBox());
			this->label8 = (gcnew System::Windows::Forms::Label());
			this->label6 = (gcnew System::Windows::Forms::Label());
			this->pixel_size = (gcnew System::Windows::Forms::TextBox());
			this->label4 = (gcnew System::Windows::Forms::Label());
			this->block_h = (gcnew System::Windows::Forms::TextBox());
			this->block_w = (gcnew System::Windows::Forms::TextBox());
			this->label5 = (gcnew System::Windows::Forms::Label());
			this->label3 = (gcnew System::Windows::Forms::Label());
			this->colorDialog1 = (gcnew System::Windows::Forms::ColorDialog());
			this->bg_color = (gcnew System::Windows::Forms::PictureBox());
			this->label7 = (gcnew System::Windows::Forms::Label());
			this->label9 = (gcnew System::Windows::Forms::Label());
			this->video_name = (gcnew System::Windows::Forms::TextBox());
			this->panel1->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->bg_color))->BeginInit();
			this->SuspendLayout();
			// 
			// openFileDialog1
			// 
			this->openFileDialog1->DefaultExt = L"mp4";
			this->openFileDialog1->Filter = L"video|*.mp4";
			this->openFileDialog1->InitialDirectory = L"c:\\\\";
			this->openFileDialog1->RestoreDirectory = true;
			this->openFileDialog1->Title = L"Open File...";
			this->openFileDialog1->FileOk += gcnew System::ComponentModel::CancelEventHandler(this, &Options::openFileDialog1_FileOk);
			// 
			// coose_file
			// 
			this->coose_file->Location = System::Drawing::Point(269, 50);
			this->coose_file->Name = L"coose_file";
			this->coose_file->Size = System::Drawing::Size(75, 23);
			this->coose_file->TabIndex = 0;
			this->coose_file->Text = L"choose File";
			this->coose_file->UseVisualStyleBackColor = true;
			this->coose_file->Click += gcnew System::EventHandler(this, &Options::chooseFileClicked);
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9.75F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label1->Location = System::Drawing::Point(0, 0);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(111, 16);
			this->label1->TabIndex = 2;
			this->label1->Text = L"Media Settings";
			// 
			// pixel_w
			// 
			this->pixel_w->Location = System::Drawing::Point(49, 28);
			this->pixel_w->Name = L"pixel_w";
			this->pixel_w->Size = System::Drawing::Size(40, 20);
			this->pixel_w->TabIndex = 3;
			this->pixel_w->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &Options::enter);
			this->pixel_w->Leave += gcnew System::EventHandler(this, &Options::reset);
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Location = System::Drawing::Point(94, 31);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(12, 13);
			this->label2->TabIndex = 4;
			this->label2->Text = L"x";
			// 
			// pixel_h
			// 
			this->pixel_h->Location = System::Drawing::Point(110, 28);
			this->pixel_h->Name = L"pixel_h";
			this->pixel_h->Size = System::Drawing::Size(40, 20);
			this->pixel_h->TabIndex = 5;
			this->pixel_h->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &Options::enter);
			this->pixel_h->Leave += gcnew System::EventHandler(this, &Options::reset);
			// 
			// goButton
			// 
			this->goButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->goButton->Location = System::Drawing::Point(177, 113);
			this->goButton->Name = L"goButton";
			this->goButton->Size = System::Drawing::Size(167, 48);
			this->goButton->TabIndex = 6;
			this->goButton->Text = L"Dew it!";
			this->goButton->UseVisualStyleBackColor = true;
			this->goButton->Click += gcnew System::EventHandler(this, &Options::goClicked);
			// 
			// file_output
			// 
			this->file_output->Location = System::Drawing::Point(12, 52);
			this->file_output->Name = L"file_output";
			this->file_output->ReadOnly = true;
			this->file_output->Size = System::Drawing::Size(251, 20);
			this->file_output->TabIndex = 1;
			this->file_output->Text = L"...";
			// 
			// progressBar1
			// 
			this->progressBar1->Location = System::Drawing::Point(12, 13);
			this->progressBar1->Name = L"progressBar1";
			this->progressBar1->Size = System::Drawing::Size(332, 23);
			this->progressBar1->TabIndex = 7;
			// 
			// panel1
			// 
			this->panel1->Controls->Add(this->fps);
			this->panel1->Controls->Add(this->label8);
			this->panel1->Controls->Add(this->label6);
			this->panel1->Controls->Add(this->pixel_size);
			this->panel1->Controls->Add(this->label4);
			this->panel1->Controls->Add(this->block_h);
			this->panel1->Controls->Add(this->block_w);
			this->panel1->Controls->Add(this->label5);
			this->panel1->Controls->Add(this->label3);
			this->panel1->Controls->Add(this->pixel_h);
			this->panel1->Controls->Add(this->label1);
			this->panel1->Controls->Add(this->pixel_w);
			this->panel1->Controls->Add(this->label2);
			this->panel1->Location = System::Drawing::Point(12, 113);
			this->panel1->Name = L"panel1";
			this->panel1->Size = System::Drawing::Size(159, 137);
			this->panel1->TabIndex = 8;
			this->panel1->Visible = false;
			// 
			// fps
			// 
			this->fps->Location = System::Drawing::Point(59, 113);
			this->fps->Name = L"fps";
			this->fps->Size = System::Drawing::Size(40, 20);
			this->fps->TabIndex = 16;
			this->fps->Text = L"20";
			this->fps->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &Options::enter);
			this->fps->Leave += gcnew System::EventHandler(this, &Options::reset);
			// 
			// label8
			// 
			this->label8->AutoSize = true;
			this->label8->Location = System::Drawing::Point(6, 116);
			this->label8->Name = L"label8";
			this->label8->Size = System::Drawing::Size(30, 13);
			this->label8->TabIndex = 15;
			this->label8->Text = L"FPS:";
			// 
			// label6
			// 
			this->label6->AutoSize = true;
			this->label6->Location = System::Drawing::Point(3, 86);
			this->label6->Name = L"label6";
			this->label6->Size = System::Drawing::Size(50, 13);
			this->label6->TabIndex = 14;
			this->label6->Text = L"Pixelsize:";
			// 
			// pixel_size
			// 
			this->pixel_size->Location = System::Drawing::Point(59, 83);
			this->pixel_size->Name = L"pixel_size";
			this->pixel_size->Size = System::Drawing::Size(40, 20);
			this->pixel_size->TabIndex = 13;
			this->pixel_size->Text = L"0.1";
			this->pixel_size->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &Options::enter);
			this->pixel_size->Leave += gcnew System::EventHandler(this, &Options::reset);
			// 
			// label4
			// 
			this->label4->AutoSize = true;
			this->label4->Location = System::Drawing::Point(3, 58);
			this->label4->Name = L"label4";
			this->label4->Size = System::Drawing::Size(42, 13);
			this->label4->TabIndex = 10;
			this->label4->Text = L"Blocks:";
			// 
			// block_h
			// 
			this->block_h->Location = System::Drawing::Point(110, 55);
			this->block_h->Name = L"block_h";
			this->block_h->Size = System::Drawing::Size(40, 20);
			this->block_h->TabIndex = 9;
			this->block_h->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &Options::enter);
			this->block_h->Leave += gcnew System::EventHandler(this, &Options::reset);
			// 
			// block_w
			// 
			this->block_w->Location = System::Drawing::Point(49, 55);
			this->block_w->Name = L"block_w";
			this->block_w->Size = System::Drawing::Size(40, 20);
			this->block_w->TabIndex = 7;
			this->block_w->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &Options::enter);
			this->block_w->Leave += gcnew System::EventHandler(this, &Options::reset);
			// 
			// label5
			// 
			this->label5->AutoSize = true;
			this->label5->Location = System::Drawing::Point(94, 58);
			this->label5->Name = L"label5";
			this->label5->Size = System::Drawing::Size(12, 13);
			this->label5->TabIndex = 8;
			this->label5->Text = L"x";
			// 
			// label3
			// 
			this->label3->AutoSize = true;
			this->label3->Location = System::Drawing::Point(3, 31);
			this->label3->Name = L"label3";
			this->label3->Size = System::Drawing::Size(32, 13);
			this->label3->TabIndex = 6;
			this->label3->Text = L"Pixel:";
			// 
			// bg_color
			// 
			this->bg_color->BackColor = System::Drawing::Color::Black;
			this->bg_color->Location = System::Drawing::Point(279, 167);
			this->bg_color->Name = L"bg_color";
			this->bg_color->Size = System::Drawing::Size(62, 17);
			this->bg_color->TabIndex = 9;
			this->bg_color->TabStop = false;
			this->bg_color->Click += gcnew System::EventHandler(this, &Options::openColorPicker);
			// 
			// label7
			// 
			this->label7->AutoSize = true;
			this->label7->Location = System::Drawing::Point(178, 168);
			this->label7->Name = L"label7";
			this->label7->Size = System::Drawing::Size(95, 13);
			this->label7->TabIndex = 10;
			this->label7->Text = L"Background Color:";
			// 
			// label9
			// 
			this->label9->AutoSize = true;
			this->label9->Location = System::Drawing::Point(9, 84);
			this->label9->Name = L"label9";
			this->label9->Size = System::Drawing::Size(38, 13);
			this->label9->TabIndex = 11;
			this->label9->Text = L"Name:";
			// 
			// video_name
			// 
			this->video_name->Location = System::Drawing::Point(53, 81);
			this->video_name->Name = L"video_name";
			this->video_name->Size = System::Drawing::Size(210, 20);
			this->video_name->TabIndex = 12;
			this->video_name->Text = L"Video Name";
			// 
			// Options
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(353, 327);
			this->Controls->Add(this->video_name);
			this->Controls->Add(this->label9);
			this->Controls->Add(this->label7);
			this->Controls->Add(this->bg_color);
			this->Controls->Add(this->panel1);
			this->Controls->Add(this->progressBar1);
			this->Controls->Add(this->goButton);
			this->Controls->Add(this->file_output);
			this->Controls->Add(this->coose_file);
			this->Name = L"Options";
			this->StartPosition = System::Windows::Forms::FormStartPosition::CenterScreen;
			this->Text = L"Dialog";
			this->panel1->ResumeLayout(false);
			this->panel1->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->bg_color))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void openFileDialog1_FileOk(System::Object^  sender, System::ComponentModel::CancelEventArgs^  e) {
		file_output->Text = openFileDialog1->FileName;
		fileChosen = true;
		panel1->Visible = true;
		mediaManager->openFile(openFileDialog1->FileName);
		update();
	}
	private: System::Void chooseFileClicked(System::Object^  sender, System::EventArgs^  e) {
		openFileDialog1->ShowDialog();
	}
	private: System::Void goClicked(System::Object^  sender, System::EventArgs^  e) {
		if (!fileChosen)
			return;
		process->setup();
		progressBar1->Increment(1);
	}
	private: System::Void openColorPicker(System::Object^  sender, System::EventArgs^  e) {
		colorDialog1->ShowDialog();
	}

	private: System::Void reset(System::Object^  sender, System::EventArgs^  e) {
		System::Windows::Forms::TextBox^ textBox = (System::Windows::Forms::TextBox^) sender;
		textBox->Text = "" + mediaManager->getProp(textBox->Name);
	}
	private: System::Void enter(System::Object^  sender, System::Windows::Forms::KeyEventArgs^  e) {
		if (e->KeyCode != Keys::Return)
			return;
		e->Handled = true;
		e->SuppressKeyPress = true;
		System::Windows::Forms::TextBox^ textBox = (System::Windows::Forms::TextBox^) sender;
		if (mediaManager->setProp(textBox->Text, textBox->Name)) {
			update();
		}
		else {
			textBox->Text = "" + mediaManager->getProp(textBox->Name);
		}
	}
};
}
