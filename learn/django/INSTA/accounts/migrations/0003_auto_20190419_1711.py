# Generated by Django 2.1.7 on 2019-04-19 17:11

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0002_auto_20190419_1710'),
    ]

    operations = [
        migrations.RenameField(
            model_name='user',
            old_name='follow',
            new_name='followings',
        ),
    ]
