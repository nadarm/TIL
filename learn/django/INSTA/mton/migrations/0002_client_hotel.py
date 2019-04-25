# Generated by Django 2.1.7 on 2019-04-11 13:25

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('mton', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Client',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=30)),
            ],
        ),
        migrations.CreateModel(
            name='Hotel',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=30)),
                ('clients', models.ManyToManyField(to='mton.Client')),
            ],
        ),
    ]