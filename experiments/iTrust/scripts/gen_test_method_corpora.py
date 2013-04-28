#!/usr/bin/python
import json
from collections import defaultdict
import os

BASE_DIR = "/Users/gdevanla/Dropbox/private/se_research/myprojects/fetch/experiments/iTrust/data"

def get_test_methods_map(fileName):

    tests = defaultdict(list)
    f = open(fileName)
    current_unittest = None
    for line in f:
        try:
            method_info = json.loads(line)["MethodInstrumentation"]
        except:
            print 'Error processing=', line
            continue
        #print method_info

        if (method_info["SuperClassName"] == "junit.framework.TestCase" and
              method_info["MethodName"].startswith("test")):

            current_unittest = method_info["ClassName"]
            continue

        if not current_unittest:
            continue

        tests[current_unittest].append(method_info)

    return tests

# This method walks the json tree and forms a string
# with all values.
# TODO: strip ";", common words like java/lang etc
def gen_str(j, s):
    if isinstance(j, basestring):
        return s + " " + j
    else:
        #print type(j), isinstance(j, dict)
        if isinstance(j, dict):
            return s + gen_str(j.values(), "")

        if isinstance(j, list):
            return s + "".join([gen_str(i, "") for i in j])

def generate_corpora(tests, destpath):
    for k, v in tests.iteritems():
        with open(os.path.join(destpath, k), "w") as f:
            f.write(gen_str(v, ""))

if __name__ == '__main__':
    t = get_test_methods_map(os.path.join(BASE_DIR, "iTrustUnitTest_1.log"))
    generate_corpora(t, os.path.join(BASE_DIR, "corpora"))
    print sorted(t.keys()), len(t)
