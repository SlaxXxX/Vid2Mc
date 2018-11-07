#pragma once
#include <msclr/marshal_cppstd.h>
#include "opencv2/core.hpp"
#include "opencv2/opencv.hpp"

namespace Vid2Mc {

	public class MediaManager {
	public:

		bool openFile(System::String^);
		bool setProp(System::String^, System::String^);
		double getProp(System::String^);

	private:
		cv::VideoCapture vc = cv::VideoCapture();

		double pixelWidth, pixelHeight;
		double blockWidth, blockHeight;
		double ratio;
		double fps = 20;
		double pixelSize = 0.1;
		int lastID = 0;
		int thisID = 0;
	};
}