import os
import sys
import boto3

class EC2_DATA:
  def __init__(self, id, pri_dns, pri_ip):
    self.id = id
    self.pri_dns = pri_dns
    self.pri_ip = pri_ip

  def value(self):
    print self.id+":"+self.pri_dns+":"+self.pri_ip

ec2_data = ''

def ec2_instance_by_name(client, name, attr):
#  print 'EC2 instance by Name ',name
  response = client.describe_instances(Filters=[
    {
      'Name': 'tag:Name',
      'Values': [name]
    }
  ])
#  print response
  inst_id = ''
  pri_dns = ''
  pri_ip = ''
  for reservation in response['Reservations']:
    for instance in reservation['Instances']:
      inst_id = instance['InstanceId']
      for nic in instance['NetworkInterfaces']:
        for pri_addrs in nic['PrivateIpAddresses']:
          pri_dns = pri_addrs['PrivateDnsName']
          pri_ip = pri_addrs['PrivateIpAddress']
  if attr == 'id':
    print inst_id
  elif attr == 'private_dns':
    print pri_dns
  elif attr == 'private_ip':
    print pri_ip

def main():
  client = boto3.client('ec2')
#  print 'sys.argv[1] = ',sys.argv[1]
#  print 'sys.argv[2] = ',sys.argv[2]
  if sys.argv[1] == 'ec2_instance_by_name':
    ec2_instance_by_name(client, sys.argv[2], sys.argv[3])

if __name__  == '__main__':
  main()
