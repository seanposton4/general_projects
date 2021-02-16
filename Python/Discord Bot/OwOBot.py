import discord
import random
from discord.utils import get
from discord.ext import commands

client = discord.Client(guild_subscriptions=True)

#Friend IDs:
bob = 134141643686281216
jeremiah = 134732551049838593
sean = 135204526096384000
raymund = 105088392311664640
mccoy = 135476652430852096
adam = 135205735326941184

@client.event
async def on_ready():
    print('Logged in as {0.user}'.format(client))
    await client.change_presence(activity=discord.Activity(type=discord.ActivityType.watching, name="OwO"))

@client.event
async def on_message(message):
    if message.author == client.user:
        return

    #Reply to user's message with :3
    if message.author.id == mccoy or message.author.id == adam:
        #:3 after everything Jeremiah says
        await message.channel.send('This source has been known to spread misinformation.')

    if ':NaM:' in message.content:
        #kick NaM spammers, but invite back
        await message.delete()
        try:
            await message.author.kick()

            link = await message.channel.create_invite(max_age = '300')
            await message.author.send(link)
            print(f'Kicked user {message.author}')
        except:
            print(f'Kick Unavailable on user {message.author}')
        finally:
            return

"""@client.event
async def on_member_join(member):
    member_role = discord.util.get(member.server.roles, id='304488179132530690')
    mod_role = discord.util.get(member.server.roles, id='304480773170528266')

    try:
        if member.id == (bob or sean):
            await client.add_roles(member, mod_role)

        await client.add_roles(member, member_role)
        await member.add_roles(member_role)
    except:
        print('Member Assign Failure')"""



"""@client.event
async def on_reaction_add(reaction, user):
    if reaction.emoji == ':NaM:':
        print('NaM This works')"""

client.run('Nzc4NDgwNTM5ODI4NjE3MjM3.X7SmgA.HWQab8eX7FCDhsj9gloISeTOdkA')
