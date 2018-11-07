#include "Process.h"

void Vid2Mc::Process::setup()
{
	if (!PathFileExistsA("SlaxX_Display_Pack")) {
		std::filesystem::copy("Template/SlaxX_Display_Pack", "SlaxX_Display_Pack", std::filesystem::copy_options::recursive);
	}
	//OutputDebugStringA(std::to_string(std::thread::hardware_concurrency()).c_str());
}
