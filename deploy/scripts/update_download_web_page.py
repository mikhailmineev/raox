#!/usr/bin/env python
# -*- coding: utf-8 -*-

import argparse
import ftp_server

HOST = 'rdo.rk9.bmstu.ru'


def get_sorted_versioned_files(versioned_files):
    return sorted(versioned_files, key=lambda versioned_file: versioned_file.file_version, reverse=True)


def generate_download_page(versioned_files, directory, program_name, repository_name):
    if not versioned_files:
        return ''

    download_page = '<H2>{program_name}</H2>\n'.format(program_name=program_name)
    current_version = ''
    for versioned_file in versioned_files:
        if current_version != versioned_file.file_version:
            current_version = versioned_file.file_version
            download_page += '<H3><A HREF="https://github.com/aurusov/{repository_name}/releases/tag/{current_version}">{current_version}</A></H3>\n'.format(current_version=current_version, repository_name=repository_name)
        download_page += '<A HREF="http://{host}/{directory}/{file_name}">{file_name}</A><BR>\n'.format(
            host=HOST,
            directory=directory,
            file_name=versioned_file.file_name)
    download_page += '<BR>\n'
    return download_page


if __name__ == "__main__":
    argument_parser = argparse.ArgumentParser()
    argument_parser.add_argument('--login', help='ftp login', default='')
    argument_parser.add_argument('--password', help='ftp password', default='')
    argument_parser.add_argument('--directory', help='ftp directory', default='tmp')
    args = argument_parser.parse_args()

    ftp = ftp_server.connect(args.login, args.password)
    raox_eclipse_files, raox_files, raox_game5_files, other_files = ftp_server.parse_files(ftp_server.get_files(ftp, args.directory))

    raox_eclipse_files = get_sorted_versioned_files(raox_eclipse_files)
    raox_files = get_sorted_versioned_files(raox_files)
    raox_game5_files = get_sorted_versioned_files(raox_game5_files)

    page = ''
    page += generate_download_page(raox_eclipse_files, args.directory, 'Rao X Eclipse', 'rdo-xtext')
    page += generate_download_page(raox_files, args.directory, 'Rao X Plugin', 'rdo-xtext')
    page += generate_download_page(raox_game5_files, args.directory, 'Rao X Game5 Plugin', 'raox-game5')

    print page
