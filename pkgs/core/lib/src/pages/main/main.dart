import 'dart:convert';
import 'dart:io';

import 'package:bird_flutter/bird_flutter.dart';
import 'package:flutter/material.dart';
import 'package:vid2minecraft_core/vid2minecraft_core.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Vid2Minecraft',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        fontFamily: 'Roboto',
      ),
      home: _mainWidget(),
    );
  }
}

Widget _mainWidget() {
  return style > $$ >> (context) {
    final bloc = $$$(() =>
        MainBloc([
          VideoProjectModel(
            id: "1",
            name: "Shia",
            bg: Colors.blue[300].value,
            fps: 10,
            horizontalResolution: 1920,
            verticalResolution: 1080,
          ),
          VideoProjectModel(
            id: "2",
            name: "Shiba",
            bg: Colors.yellow[300].value,
            fps: 10,
            horizontalResolution: 1920,
            verticalResolution: 1080,
          ),
          VideoProjectModel(
            id: "3",
            name: "Shibuya",
            bg: Colors.black.value,
            fps: 10,
            horizontalResolution: 1020,
            verticalResolution: 780,
          ),
          VideoProjectModel(
            id: "4",
            name: "Shonen",
            bg: Colors.red[300].value,
            fps: 10,
            horizontalResolution: 1020,
            verticalResolution: 780,
          ),
        ]));

    return center() > onColumnMinCenterCenter() >> [
      title,
      verticalSpace(28.0),

      onListView(shrinkWrap: true) >> [

        center()
        & height(200)
        & width(500)
        & material(7.0, borderRadius: BorderRadius.circular(12.0), color: Colors.grey[900].withOpacity(0.8))
            > $$ >> (context) {
          final model = bloc.models.hook;
          final selectedID = bloc.selectedId.hook;

          return onListView(shrinkWrap: true) >> model.map((m) {
            return ListTile(
              title: textColor(Colors.white) > Text(m.name),
              subtitle: onRowMinStartCenter() >> [
                textColor(Colors.white)
                    > Text("${m.horizontalResolution}x${m.verticalResolution}"),
                horizontalSpace(4.0),
                textColor(Colors.white)
                    > Text("${m.fps} FPS"),
                horizontalSpace(4.0),
                square(8.0)
                & clipCircular(15.0)
                & bgColor(Color(m.bg))
                    > nothing,
              ],
              onTap: () => bloc.selectModel(m),
              trailing: selectedID.map((a) => a == m.id) | false
                        ? Icon(Icons.check, color: Colors.white)
                        : null,
            );
          });
        },

        verticalSpace(28.0),

        center() > RaisedButton(
          child: const Text("Test"),
          onPressed: () {
            Process.start("java", [
              "-jar",
              "/Users/valauskasmodestas/Desktop/jvid2cmd-0.1-Test.jar",
              "test",
            ]).catchError(print).then((a) {
              a.stdout.map(utf8.decode).listen((String data) {
                print("Data: " + data.toString());
              }, onError: (dynamic err) {
                print("Error: " + err.toString());
              }, onDone: () {
                print("Done");
              });
              a.stderr.map(utf8.decode).listen((data) {
                print("ERR: Data: " + data.toString());
              }, onError: (dynamic err) {
                print("ERR: Error: " + err.toString());
              }, onDone: () {
                print("ERR: Done");
              });
              print(a);
            });
          },
        ),
        verticalSpace(28.0),

        center() > RaisedButton(
          child: const Text("Let's go"),
          onPressed: bloc.toggleLoading,
        ),
        verticalSpace(28.0),
        $$ >> (context) {
          final isLoading = bloc.isLoading.hook;
          if (isLoading) {
            return center()
            & width(150.0)
            & clipCircular(8.0)
                > const LinearProgressIndicator(
                  valueColor: AlwaysStoppedAnimation(Colors.grey),
                  backgroundColor: Colors.white,
                );
          } else {
            return nothing;
          }
        },
      ],
    ];
  };
}

Widget title = relativeHeight(0.2)
& villainScale(from: 1 / 1.02, to: 1.02)
    .inTimeMS(1000)
    .loopPingPong()
& padding(horizontal: 32.0)
    > FadeInImage(
      placeholder: emptyImage,
      image: const AssetImage("assets/title.png"),
    );

Applicator style = scaffold(color: Colors.transparent)
& bgColor(Colors.grey)
& apply((child) {
  const _scale = 2.0;
  return
    apply
    & villainTranslateX(from: -10.0, to: 10.0, curve: Curves.linear)
        .inTimeMS(3000)
        .loopPingPong()
    & scale(_scale)
        > Container(
      decoration: const BoxDecoration(
        image: DecorationImage(
          fit: BoxFit.cover,
          image: AssetImage("assets/bg.jpg"),
          repeat: ImageRepeat.repeat,
        ),
      ),
      child: apply
      & scale(1 / _scale)
      & villainTranslateX(from: 10.0, to: -10.0, curve: Curves.linear)
          .inTimeMS(3000)
          .loopPingPong()
          > child,
    );
});