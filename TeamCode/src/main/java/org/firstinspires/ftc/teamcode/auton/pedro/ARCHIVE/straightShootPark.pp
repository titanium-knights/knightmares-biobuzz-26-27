{
  "startPoint": {
    "x": 56.815561959654175,
    "y": 8,
    "headingDeg": 90,
    "locked": false,
    "name": "star"
  },
  "lines": [
    {
      "kind": "atomic",
      "id": "line-mvprlazx-ace9ek",
      "name": "ToShoot",
      "endPoint": {
        "x": 56,
        "y": 36
      },
      "controlPoints": [],
      "heading": {
        "type": "linear",
        "startDeg": 0,
        "endDeg": 0
      },
      "color": "#ffc516",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "kind": "atomic",
      "id": "line-mvprzxi7-3fujue",
      "endPoint": {
        "x": 37.67002881844381,
        "y": 36
      },
      "controlPoints": [],
      "heading": {
        "type": "tangential",
        "reverse": true
      },
      "color": "#76AC97",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "name": "MoveSideways"
    },
    {
      "kind": "atomic",
      "id": "line-mvps1iew-us8xbb",
      "endPoint": {
        "x": 38,
        "y": 105
      },
      "controlPoints": [],
      "heading": {
        "type": "linear",
        "reverse": true,
        "startDeg": 0,
        "endDeg": 0
      },
      "color": "#CCA6DB",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "name": "MoveForwardTowardsPark"
    },
    {
      "kind": "atomic",
      "id": "line-mvps3ruw-1d5uog",
      "endPoint": {
        "x": 8,
        "y": 105
      },
      "controlPoints": [],
      "heading": {
        "type": "linear",
        "reverse": true,
        "startDeg": 0,
        "endDeg": 0
      },
      "color": "#55A56A",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "name": "Park"
    }
  ],
  "shapes": [
    {
      "id": "triangle-1",
      "name": "Red Goal",
      "vertices": [
        {
          "x": 141.5,
          "y": 70
        },
        {
          "x": 141.5,
          "y": 141.5
        },
        {
          "x": 118.3,
          "y": 141.5
        },
        {
          "x": 135.5,
          "y": 118
        },
        {
          "x": 136.3,
          "y": 70.2
        }
      ],
      "color": "#dc2626",
      "fillColor": "#ff6b6b"
    },
    {
      "id": "triangle-2",
      "name": "Blue Goal",
      "vertices": [
        {
          "x": 6.2,
          "y": 116.9
        },
        {
          "x": 25,
          "y": 141.5
        },
        {
          "x": 0,
          "y": 141.5
        },
        {
          "x": 0,
          "y": 70
        },
        {
          "x": 6,
          "y": 70
        }
      ],
      "color": "#2563eb",
      "fillColor": "#60a5fa"
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-mvprlazx-ace9ek"
    },
    {
      "kind": "path",
      "lineId": "line-mvprzxi7-3fujue"
    },
    {
      "kind": "path",
      "lineId": "line-mvps1iew-us8xbb"
    },
    {
      "kind": "path",
      "lineId": "line-mvps3ruw-1d5uog"
    }
  ],
  "fieldPoints": [],
  "activePaths": [],
  "settings": {
    "xVelocity": 75,
    "yVelocity": 65,
    "aVelocity": 3.141592653589793,
    "kFriction": 0.1,
    "rWidth": 16,
    "rHeight": 16,
    "safetyMargin": 1,
    "maxVelocity": 40,
    "maxAcceleration": 30,
    "maxDeceleration": 30,
    "fieldMap": "biobuzz.webp",
    "robotImage": "/robot.png",
    "showGhostPaths": false,
    "showOnionLayers": false,
    "onionLayerSpacing": 3,
    "onionColor": "#dc2626",
    "onionNextPointOnly": false,
    "showHeadingArrow": false,
    "showCurrentTValue": false,
    "leftPanelWidth": 145,
    "rightPanelWidth": 620,
    "headingArrowLength": 50,
    "headingArrowColor": "#ffffff",
    "headingArrowThickness": 2,
    "pathOpacity": 1,
    "leftPanelMinWidth": 0,
    "rightPanelMinWidth": 0,
    "penToolMaxPaths": 8,
    "curveThroughMaxPoints": 4,
    "experimentalFeatures": {
      "optimize": false,
      "curveThrough": false
    }
  },
  "version": "1.5.0",
  "timestamp": "2026-10-26T21:43:51.239Z"
}