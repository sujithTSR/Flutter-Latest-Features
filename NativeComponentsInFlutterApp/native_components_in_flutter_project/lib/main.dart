import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      home: MyHomePage(title: 'Counter App Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {

  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
    String _response = "Waiting for the response from Android Code.!";

  static const platform = const MethodChannel('flutter.native.within/channel');
  Future _invokeAndroid() async {
//    Smoke-Test to check if the channel communication is working fine with multiple devices.
    String response = "";
    try {
      final String result = await platform.invokeMethod('helloFromNativeCode');
      response = result;
      print(response);
    } on PlatformException catch (e) {
      response = "Failed to Invoke: '${e.message}'.";
      print(response);
    }
    setState(() {
      _response = response;
    });
  }
  _MyHomePageState();

  @override
  void initState() {
    platform.setMethodCallHandler(methodHandler);
  }

  Future<dynamic> methodHandler(MethodCall methodCall) async {
    print(methodCall.method);
    switch (methodCall.method) {
      case 'fromAndroid':
        setState(() {
          print(methodCall.method + " Setting state");
          _response = "Back to Flutter";
        });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              '$_response',
              key: Key('__response'),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        key: Key('__button'),
        onPressed: _invokeAndroid,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}