#-------------------------------------------------------------------------------
# Name:        LoginToUploadBiobrick
# Purpose:     Login in http://parts.igem.org and create a new Biobrick
#
# Author:      SJTU-Software
#
# Created:     01/10/2014
# Copyright:   (c) SJTU-Software 2014
# Licence:     <licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python

import urllib2, cookielib, urllib, sys, base64
from bs4 import BeautifulSoup

def partsUpload():
    
    #file input
    file_object = open('upload')
    username = file_object.readline().strip('\n')
    pw = file_object.readline().strip('\n')
    part_type = file_object.readline().strip('\n')
    short_description = file_object.readline().strip('\n')
    long_description = file_object.readline().strip('\n')
    source = file_object.readline().strip('\n')
    notes = file_object.readline().strip('\n')
    nickname = file_object.readline().strip('\n')
    designer = file_object.readline().strip('\n')
    parameters = eval(file_object.readline().strip('\n'))
    categories = eval(file_object.readline().strip('\n'))
    sequence = file_object.readline().strip('\n')
    features = eval(file_object.readline().strip('\n'))

    #Decode password
    pw = base64.decodestring(pw)
    
    #enable Cookies
    cj=cookielib.CookieJar()
    opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
    urllib2.install_opener(opener)
    

    #Login igem.org
    postdata = urllib.urlencode({'Login':'Login','username':username,'password':pw})
    req=urllib2.Request(url='https://igem.org/Login2',data=postdata)
    response = urllib2.urlopen(req)
    
    
    #confirmed login or not
    content = urllib2.urlopen('http://igem.org/Login_Confirmed').read()
    soup = BeautifulSoup(content)
    confirme = str(soup.find('div').string).strip().decode('utf8')

    #if login fail, return
    if confirme=='None':
        print -1
        return

    #find avaliable part_name
    context = urllib2.urlopen('http://parts.igem.org/cgi/partsdb/add_part_b.cgi').read()
    soup = BeautifulSoup(context)
    part_name = str(soup.form.find('table',{'id':'table_info'}).find_all('td')[4].get_text())
    print part_name
    

    #Create a new Biobrick
    postdata = urllib.urlencode({'id':'0','group_1859':'on','part_name':part_name,
        'type':part_type,'short_description':short_description,
        'long_description':long_description,
        'source':source,'notes':notes,'proceed':'Go on to enter the sequence and add feature annotations'})
    req=urllib2.Request(url='http://parts.igem.org/cgi/partsdb/add_part_b.cgi',data=postdata)
    response = urllib2.urlopen(req)
    
    
    #get part_id
    infor_url = 'http://parts.igem.org/cgi/partsdb/part_info.cgi?part_name='+part_name
    context = urllib2.urlopen('http://parts.igem.org/cgi/xml/part.cgi?part='+part_name).read()
    soup = BeautifulSoup(context)
    part_id = str(soup.part_id.get_text())
    
    
    #Modify Information
    postdata = urllib.urlencode({'id':part_id,
        'hidden_header':'14',
        'new_part_name':part_name,
        'Apply new name':'Apply new name',
        'short':short_description,
        'type':part_type,
        'nickname':nickname,
        'author':designer,
        'works':'None',
        'favorite':'No',
        'delete_part':'Leave Unchanged'})
    req=urllib2.Request(url=infor_url,data=postdata)
    response = urllib2.urlopen(req)


    #Modify Parameters
    parameters = sorted(parameters.iteritems(), key = lambda asd:asd[0])

    data_head = {'id':part_id,
            'parameters_center':'',
            'parameters_right':'',
            'hidden_parameters':'999'}
    for i in range(len(parameters)):
        data_current = {'parameter_name_999':parameters[i][0],
            'parameter_value_999':parameters[i][1]}
        data_final = dict(data_head, **data_current)
        postdata = urllib.urlencode(data_final)
        req=urllib2.Request(url=infor_url,data=postdata)
        response = urllib2.urlopen(req)
        data_head = dict(data_head,**{'parameter_name_'+str(i+1):parameters[i][0],
            'parameter_value_'+str(i+1):parameters[i][1]})


    #Modify Categories
    categories = sorted(categories)
    data_head = {'id':part_id,
            'categories_center':'',
            'categories_right':'',
            'hidden_categories':'999'}
    for i in range(len(categories)):
        data_current = {'categories_999':categories[i]}
        data_final = dict(data_head, **data_current)
        postdata = urllib.urlencode(data_final)
        req=urllib2.Request(url=infor_url,data=postdata)
        response = urllib2.urlopen(req)
        data_head = dict(data_head,**{'categories_'+str(i+1):categories[i]})


    #Modify Sequence
    postdata = urllib.urlencode({'id':part_id,
        'dna_center':'',
        'dna_right':'',
        'hidden_dna':'',
        'Edit_dna':'1',
        'Save_dna.x':'1',
        'Cancel.x':'0',
        'plurality':'basic',
        'sequence':sequence})
    req=urllib2.Request(url='http://parts.igem.org/partsdb/edit_seq.cgi?part='+str(part_name),data=postdata)
    response = urllib2.urlopen(req)
    

    #Modify Features
    features_id=[]
    features_head ={'id':part_id,'features_center':'','features_right':'','hidden_features':''}
    for features_item in features:
        context = urllib2.urlopen('http://parts.igem.org/partsdb/edit_seq.cgi?id='+str(part_id)+'&New_feature=1').read()
        context = urllib2.urlopen('http://parts.igem.org/cgi/xml/part.cgi?part='+part_name).read()
        soup = BeautifulSoup(context)
        result = soup.features.find_all('id')
        for result_item in result:
            j = result_item.get_text()
            if str(j) not in features_id:
                features_id.append(str(j))
                features_head['hidden_features'] = str(j)
                features_current = {
                    'feature_type_'+str(j):features_item[0],
                    'feature_label_'+str(j):features_item[1],
                    'feature_start_'+str(j):features_item[2],
                    'feature_end_'+str(j):features_item[3],
                    'feature_reverse_'+str(j):features_item[4]}
                features_head = dict(features_head,**features_current)
                postdata = urllib.urlencode(features_head)
                req=urllib2.Request(url='http://parts.igem.org/partsdb/edit_seq.cgi?part='+str(part_name),data=postdata)
                response = urllib2.urlopen(req)
                
    
partsUpload()
