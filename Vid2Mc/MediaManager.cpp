#include "MediaManager.h"

bool Vid2Mc::MediaManager::openFile(System::String^ path)
{
	std::string pathAsStd = msclr::interop::marshal_as<std::string>(path);
	bool success = vc.open(pathAsStd);
	pixelWidth = vc.get(cv::CAP_PROP_FRAME_WIDTH);
	pixelHeight = vc.get(cv::CAP_PROP_FRAME_HEIGHT);
	blockWidth = pixelWidth * pixelSize;
	blockHeight = pixelHeight * pixelSize;
	ratio = pixelWidth / pixelHeight;
	return success;
}

bool Vid2Mc::MediaManager::setProp(System::String^ value, System::String^ name)
{
	std::string std_value = msclr::interop::marshal_as<std::string>(value);
	std::replace(std_value.begin(), std_value.end(), ',', '.');
	double val;
	try {
		val = std::stod(std_value);
	}
	catch (...) {
		return false;
	}

	if (name == "pixel_w") {
		pixelWidth = val;
		pixelHeight = val / ratio;
		if (thisID != 1)
			lastID = thisID;
		if (lastID != 2) {
			blockWidth = pixelWidth * pixelSize;
			blockHeight = pixelHeight * pixelSize;
		}
		else {
			pixelSize = blockWidth / pixelWidth;
		}
		thisID = 1;
		if (lastID == 0)
			lastID = thisID;
	}
	else if (name == "pixel_h") {
		pixelHeight = val;
		pixelWidth = val * ratio;
		if (thisID != 1)
			lastID = thisID;
		if (lastID != 2) {
			blockWidth = pixelWidth * pixelSize;
			blockHeight = pixelHeight * pixelSize;
		}
		else {
			pixelSize = blockWidth / pixelWidth;
		}
		thisID = 1;
		if (lastID == 0)
			lastID = thisID;
	}
	else if (name == "block_w") {
		blockWidth = val;
		blockHeight = val / ratio;
		if (thisID != 2)
			lastID = thisID;
		if (lastID != 1) {
			pixelWidth = blockWidth / pixelSize;
			pixelHeight = blockHeight / pixelSize;
		}
		else {
			pixelSize = blockWidth / pixelWidth;
		}
		thisID = 2;
		if (lastID == 0)
			lastID = thisID;
	}
	else if (name == "block_h") {
		blockHeight = val;
		if (thisID != 2)
			lastID = thisID;
		blockWidth = val * ratio;
		if (lastID != 1) {
			pixelWidth = blockWidth / pixelSize;
			pixelHeight = blockHeight / pixelSize;
		}
		else {
			pixelSize = blockWidth / pixelWidth;
		}
		thisID = 2;
		if (lastID == 0)
			lastID = thisID;
	}
	else if (name == "pixel_size") {
		pixelSize = val;
		if (thisID != 3)
			lastID = thisID;
		if (lastID != 2) {
			blockWidth = pixelWidth * pixelSize;
			blockHeight = pixelHeight * pixelSize;
		}
		else {
			pixelWidth = blockWidth / pixelSize;
			pixelHeight = blockHeight / pixelSize;
		}
		thisID = 3;
		if (lastID == 0)
			lastID = thisID;
	}
	else if (name == "fps") {
		fps = val;
	}
	return true;
}

double Vid2Mc::MediaManager::getProp(System::String ^ name)
{
	if (name == "pixel_w")
		return pixelWidth;
	if (name == "pixel_h")
		return pixelHeight;
	if (name == "block_w")
		return blockWidth;
	if (name == "block_h")
		return blockHeight;
	if (name == "pixel_size")
		return pixelSize;
	if (name == "fps")
		return fps;
}


