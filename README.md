enStratus Java API call example
===============================

## Build

   mvn clean install -Plive

Build the self-contained "fat" jar:

    mvn package

This creates <code>enstratus-api-X.Y.Z.jar</code>.

## Setup

Export your API credentials (see 'Manage My API Keys' in the web application for values):

    export ENSTRATUS_API_ACCESS_KEY=12345
    export ENSTRATUS_API_SECRET_KEY=0xba5eba11

Optional, default is <code>https://api.enstratus.com</code>

    export ENSTRATUS_API_ENDPOINT=https://api.enstratus.com

Optional, default is <code>2012-06-15</code>:

    export ENSTRATUS_API_VERSION=2012-06-15

