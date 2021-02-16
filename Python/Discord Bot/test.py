import discord
from discord.ext import commands

client = commands.Bot(command_prefix = '.')

@client.event
async def on_ready():
    print('Bot ready!')

@client.command()
@commands.has_permissions(kick_members = True)
async def kick(ctx, member:discord.Member):
    await member.kick()

client.run('Nzc4NDgwNTM5ODI4NjE3MjM3.X7SmgA.HWQab8eX7FCDhsj9gloISeTOdkA')