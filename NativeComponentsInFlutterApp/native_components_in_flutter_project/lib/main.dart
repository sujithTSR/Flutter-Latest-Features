import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
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
  static const platform = const MethodChannel('flutter.native.within/channel');
  String _response = "Waiting for the response from Native Code.!";
  Future _invokeAndroid() async {
    String response = "";
    try {
      final String result = await platform.invokeMethod('helloFromNativeCode');
      response = result;
    } on PlatformException catch (e) {
      response = "Failed to Invoke: '${e.message}'.";
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
    switch (methodCall.method) {
      case 'fromAndroid':
        setState(() {
          _response = "Back to Flutter";
        });
        return 'some string';
      case 'bar':
        return 123.0;
      default:
      // todo - throw not implemented
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
                '$_response'
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _invokeAndroid,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}
