server {
    host = '0.0.0.0'
    port = 8084
    ioWorkersCount = 1
    workersCount = 1
    ttl = 5 * 60 * 1000
    context = 'jrt'
    basicAuth {
        username = 'user'
        password = 'pass'
    }

//    ssl {
//        cert = '/etc/ssl/certs/hostname.crt'
//        key = '/etc/ssl/private/hostname.key'
//    }
}

jrt {
    rtorrent {
        host = 'localhost'
        port = 5000
    }

    downloads = '/tmp/'
}