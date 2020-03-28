// Copyright 2018 The Flutter team. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Finance App',
      theme: ThemeData(
        primaryColor: Colors.deepOrangeAccent,
      ),
      home: HomeScreen(),
    );
  }
}

class HomeScreenState extends State<HomeScreen> {
  final _biggerFont = const TextStyle(fontSize: 18.0);
  final _menuOptions = ["Weekly Report","Add/Manage Costs","Past Expenses"];
  var _currentOption = -1;
  var _pastExpenses = <String>[];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Finance App'),
        actions: <Widget>[
          //IconButton(icon: Icon(Icons.list), onPressed: _pushMenu),
        ],
      ),
      body: _mainMenu(),
    );
  }

  Widget _mainMenu() {
    return ListView.builder(
        padding: const EdgeInsets.all(16.0),
        itemBuilder: (context, i) {
          if(i ~/ 2 > _menuOptions.length-1) return null;
          if (i.isOdd) return Divider();
          return _menuItem(_menuOptions[i ~/ 2]);
        });
  }

  Widget _menuItem(String option) {
    return ListTile(
      title: Text(
        option,
        style: _biggerFont,
      ),
      onTap: () {
        setState(() {
          _currentOption = _menuOptions.indexOf(option);
        });
        _changeMenu(_currentOption);
      },
    );
  }

  void _changeMenu(int option) {
    var _tempData = "";
    Firestore.instance
        .collection('users')
        .document('user1')
        .get()
        .then((DocumentSnapshot ds) {
          _tempData += ds["expenses"][0];
    });
    if(option == 0) {
      Navigator.of(context).push(
          MaterialPageRoute<void>(
              builder: (BuildContext context) {
                return Scaffold(
                  appBar: AppBar(
                    title: Text('Weekly Report'),
                  ),
                  body: Text('hello, world! ' + _tempData),
                );
              }
          ),
      );
    }
    if(option == 1) {
      Navigator.of(context).push(
        MaterialPageRoute<void>(
            builder: (BuildContext context) {
              return Scaffold(
                appBar: AppBar(
                  title: Text('Add/Manage Costs'),
                ),
                body: Text('hello world'),
              );
            }
        ),
      );
    }
    if(option == 2) {
      Navigator.of(context).push(
        MaterialPageRoute<void>(
            builder: (BuildContext context) {
              return Scaffold(
                appBar: AppBar(
                  title: Text('Past Expenses'),
                ),
                body: Text('hello world'),
              );
            }
        ),
      );
    }
  }
}

class HomeScreen extends StatefulWidget {
  @override
  HomeScreenState createState() => HomeScreenState();
}

