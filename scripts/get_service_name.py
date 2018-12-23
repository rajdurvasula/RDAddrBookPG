import os
import sys
import json

with open('taskdef.json') as f:
   data = json.load(f)
print data['containerDefinitions'][0]['name']
