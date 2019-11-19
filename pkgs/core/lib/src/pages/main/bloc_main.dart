import 'package:bird/bird.dart';
import 'package:vid2minecraft_core/src/model/video_project_model.dart';

// ignore_for_file: invalid_use_of_protected_member
// ignore_for_file: type_annotate_public_apis
class MainBloc extends HookBloc {
  final _isLoading = false.$;
  final _selectedID = none<String>().$;

  Wave<List<VideoProjectModel>> models;
  Wave<bool> isLoading;
  Wave<Option<String>> selectedId;

  MainBloc(List<VideoProjectModel> testData) {
    final _models = testData.sea & d;
    models = _models;
    isLoading = _isLoading;
    selectedId = _selectedID;
  }

  void toggleLoading() => _isLoading.add(!_isLoading.value);

  void selectModel(VideoProjectModel m) => _selectedID.add(some(m.id));
}