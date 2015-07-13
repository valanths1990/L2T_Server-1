/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package l2tserver.gameserver.network.serverpackets;

import java.util.ArrayList;

import l2tserver.gameserver.model.L2ItemInstance;
import l2tserver.gameserver.model.itemcontainer.PcInventory;

/**
 * Structure:<BR>
 * FE C5 00 01 00 29 95 15 40 8B 3C 00 00 00 00 00
 * 00 E2 01 00 00 00 00 00 00 03 00 00 00 00 00 00
 * 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF F1
 * D8 FF FF FE FF 00 00 00 00 00 00 00 00 00 00 00
 * 00 00 00 00 00 00 00 00 00 00 00
 * <BR>
 * 
 * @author JIV
 * 

 */
public class ExQuestItemList extends L2ItemListPacket
{
	private static final String _S__FE_C6_EXQUESTITEMLIST = "[S] FE:C6 ExQuestItemList";
	
	private ArrayList<L2ItemInstance> _items;
	private PcInventory _inventory;
	
	public ExQuestItemList(ArrayList<L2ItemInstance> items, PcInventory inv)
	{
		_items = items;
		_inventory = inv;
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0xC7);
		
		writeH(_items.size());
		for (L2ItemInstance item : _items)
			writeItem(item);
		if (_inventory.hasInventoryBlock())
		{
			writeH(_inventory.getBlockItems().length);
			writeC(_inventory.getBlockMode());
			for (int i : _inventory.getBlockItems())
				writeD(i);
		}
		else
			writeH(0x00);
	}
	
	/* (non-Javadoc)
	 * @see l2tserver.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_C6_EXQUESTITEMLIST;
	}
	
}