import 'package:flutter/material.dart';

class VideoProjectModel {
  final String id;
  final String name;
  final int fps;
  final int horizontalResolution;
  final int verticalResolution;
  final int bg;

//<editor-fold desc="Data Methods" defaultstate="collapsed">

  const VideoProjectModel({
    @required this.id,
    @required this.name,
    @required this.fps,
    @required this.horizontalResolution,
    @required this.verticalResolution,
    @required this.bg,
  });

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
          (other is VideoProjectModel &&
              runtimeType == other.runtimeType &&
              id == other.id &&
              name == other.name &&
              fps == other.fps &&
              horizontalResolution == other.horizontalResolution &&
              verticalResolution == other.verticalResolution &&
              bg == other.bg
          );

  @override
  int get hashCode =>
      id.hashCode ^
      name.hashCode ^
      fps.hashCode ^
      horizontalResolution.hashCode ^
      verticalResolution.hashCode ^
      bg.hashCode;

  @override
  String toString() {
    // ignore: prefer_adjacent_string_concatenation
    return 'VideoProjectModel{' +
        ' id: $id,' +
        ' name: $name,' +
        ' fps: $fps,' +
        ' horizontalResolution: $horizontalResolution,' +
        ' verticalResolution: $verticalResolution,' +
        ' bg: $bg,' +
        '}';
  }

  VideoProjectModel copyWith({
    String id,
    String name,
    int fps,
    int horizontalResolution,
    int verticalResolution,
    int bg,
  }) {
    return VideoProjectModel(
      id: id ?? this.id,
      name: name ?? this.name,
      fps: fps ?? this.fps,
      horizontalResolution: horizontalResolution ?? this.horizontalResolution,
      verticalResolution: verticalResolution ?? this.verticalResolution,
      bg: bg ?? this.bg,
    );
  }

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'id': this.id,
      'name': this.name,
      'fps': this.fps,
      'horizontalResolution': this.horizontalResolution,
      'verticalResolution': this.verticalResolution,
      'bg': this.bg,
    };
  }

  factory VideoProjectModel.fromMap(Map<String, dynamic> map) {
    return VideoProjectModel(
      id: map['id'] as String,
      name: map['name'] as String,
      fps: map['fps'] as int,
      horizontalResolution: map['horizontalResolution'] as int,
      verticalResolution: map['verticalResolution'] as int,
      bg: map['bg'] as int,
    );
  }

//</editor-fold>
}