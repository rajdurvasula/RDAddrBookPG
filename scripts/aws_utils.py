import os
import sys
import json
import subprocess

from pprint import pprint

def ec2_instance_by_name(name, attr):
  cmd = ['aws','ec2','describe-instances','--filters']
  cmd.append('Name=tag:Name,Values='+name)
  cmd_str = ' '.join(cmd)
  print 'Calling ',cmd_str
  process = subprocess.Popen(cmd, stdout=subprocess.PIPE)
  out, err =  process.communicate()
  pri_dns = ''
  pri_ip = ''
  if process.returncode == 0:
    response = json.loads(out)
    for reservation in response['Reservations']:
      for instance in reservation['Instances']:
        for nic in instance['NetworkInterfaces']:
          for pri_addrs in nic['PrivateIpAddresses']:
            pri_dns = pri_addrs['PrivateDnsName']
            pri_ip = pri_addrs['PrivateIpAddress']
  if attr == 'private_dns':
    print pri_dns
  elif attr == 'private_ip':
    print pri_ip

def main():
#  print 'sys.argv[1] = ',sys.argv[1]
#  print 'sys.argv[2] = ',sys.argv[2]
  if sys.argv[1] == 'ec2_instance_by_name':
    ec2_instance_by_name(sys.argv[2], sys.argv[3])

if __name__  == '__main__':
  main()
